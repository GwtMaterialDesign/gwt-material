/*!
 * Lolliclock v0.1.0 
 * Matthew Krick 2015
 * Inspired by Google's material design & ClockPicker v0.0.7 (http://weareoutman.github.io/clockpicker/)
 */

;
(function () {
  var $ = window.jQuery;

  // Default options
  LolliClock.DEFAULTS = {
    startTime: '',	      // default time, '' or 'now' or 'H:MM AM'
    autoclose: false,    	// show Cancel/OK buttons
    vibrate: true        	// vibrate the device when dragging clock hand
  };

  // Listen touch events in touch screen device, instead of mouse events in desktop.
  var touchSupported = 'ontouchstart' in window;
  var mousedownEvent = 'mousedown' + ( touchSupported ? ' touchstart' : '');
  var mousemoveEvent = 'mousemove.lolliclock' + ( touchSupported ? ' touchmove.lolliclock' : '');
  var mouseupEvent = 'mouseup.lolliclock' + ( touchSupported ? ' touchend.lolliclock' : '');

  // Vibrate the device if supported
  var vibrate = navigator.vibravarte ? 'vibrate' : navigator.webkitVibrate ? 'webkitVibrate' : null;

  var svgNS = 'http://www.w3.org/2000/svg';

  function createSvgElement(name) {
    return document.createElementNS(svgNS, name);
  }

  function leadingZero(num) {
    return (num < 10 ? '0' : '') + num;
  }

  // Get a unique id
  var idCounter = 0;

  function uniqueId(prefix) {
    var id = ++idCounter + '';
    return prefix ? prefix + id : id;
  }

  // Clock size
  var dialRadius = 84;
  var radius = 70;
  var tickRadius = 12;
  var diameter = dialRadius * 2;
  var duration = 350;

  // Popover template
  var tpl = [
    '<div class="lolliclock-popover">',
    '<div class="lolliclock-header">',
    '<div class="lolliclock-time">',
    '<div class="lolliclock-hours lolliclock-primary-text">',
    '<div class="lolliclock-time-old"></div>',
    '<div class="lolliclock-time-new"></div>',
    '</div>',
    '<span class="lolliclock-colon">:</span>',
    '<div class="lolliclock-minutes">',
    '<div class="lolliclock-time-old"></div>',
    '<div class="lolliclock-time-new"></div>',
    '</div>',
    '</div>',
    '<span class="lolliclock-am-pm"></span>',
    '</div>',
    '<div class="popover-content">',
    '<div class="lolliclock-plate">',
    '<div class="lolliclock-canvas"></div>',
    '<div class="lolliclock-dial lolliclock-dial-hours"></div>',
    '<div class="lolliclock-dial lolliclock-dial-minutes lolliclock-dial-out"></div>',
    '</div>',
    '<div class="lolliclock-ampm-block">',
    '<div id="lolliclock-btn-am" class="lolliclock-ampm-btn">',
    '<div class="lolliclock-btn-background"></div>',
    '<div class="lolliclock-btn-text">AM</div>',
    '</div>',
    '<div style="flex: 1;"></div>',
    '<div id="lolliclock-btn-pm" class="lolliclock-ampm-btn">',
    '<div class="lolliclock-btn-background"></div>',
    '<div class="lolliclock-btn-text">PM</div>',
    '</div>',
    '</div>',
    '</div>',
    '</div>'
  ].join('');

  // LolliClock
  function LolliClock(element, options) {
    var popover = $(tpl);
    var plate = popover.find('.lolliclock-plate');
    var hoursView = popover.find('.lolliclock-dial-hours');
    var minutesView = popover.find('.lolliclock-dial-minutes');
    var isInput = element.prop('tagName') === 'INPUT';
    var input = isInput ? element : element.find('input');
    var self = this;

    this.id = uniqueId('lolli');
    this.element = element;
    this.options = options;
    this.isAppended = false;
    this.isShown = false;
    this.currentView = 'hours';
    this.isInput = isInput;
    this.input = input;
    this.popover = popover;
    this.plate = plate;
    this.hoursView = hoursView;
    this.minutesView = minutesView;
    this.header = popover.find('.lolliclock-header');
    this.spanHours = popover.find('.lolliclock-hours');
    this.spanMinutes = popover.find('.lolliclock-minutes');
    this.spanNewTime = popover.find('.lolliclock-time-new');
    this.spanOldTime = popover.find('.lolliclock-time-old');
    this.spanAmPm = popover.find('.lolliclock-am-pm');
    this.amOrPm = "PM";
    this.AmPmButtons = popover.find('.lolliclock-ampm-btn');
    this.amButton = popover.find('#lolliclock-btn-am');
    this.pmButton = popover.find('#lolliclock-btn-pm');

    //var exportName = (this.input[0].name || this.input[0].id) + '-export';
    //this.dateTimeVal = $('<input type="hidden" id="' + exportName + '"></input>').insertAfter(input);
    // If autoclose is not setted, append a button
    if (!options.autoclose) {
      this.popover.css('height', '380px');
      var $closeButtons = $('<div class="lolliclock-buttons"></div>').appendTo(popover);
      $('<div class="lolliclock-button">Cancel</div>')
        .click($.proxy(this.hide, this))
        .appendTo($closeButtons);
      $('<div class="lolliclock-button">OK</div>')
        .click($.proxy(this.done, this))
        .appendTo($closeButtons);
      this.closeButtons = popover.find('.lolliclock-button');
    }

    // Show or toggle
    input.on('focus.lolliclock click.lolliclock', $.proxy(this.show, this));

    // Build ticks
    var tickTpl = $('<div class="lolliclock-tick"></div>');
    var i, tick, radian;

    // Hours view
    for (i = 1; i < 13; i++) {
      tick = tickTpl.clone();
      radian = i / 6 * Math.PI;
      tick.css({
        left: dialRadius + Math.sin(radian) * radius - tickRadius,
        top: dialRadius - Math.cos(radian) * radius - tickRadius
      });
      tick.html(i);
      hoursView.append(tick);
    }

    // Minutes view
    for (i = 0; i < 60; i += 5) {
      tick = tickTpl.clone();
      radian = i / 30 * Math.PI;
      tick.css({
        left: dialRadius + Math.sin(radian) * radius - tickRadius,
        top: dialRadius - Math.cos(radian) * radius - tickRadius
      });
      tick.html(leadingZero(i));
      minutesView.append(tick);
    }

    //Move click to nearest tick
    plate.on(mousedownEvent, mousedown);

    // Mousedown or touchstart
    function mousedown(e) {
      var offset = plate.offset(),
        isTouch = /^touch/.test(e.type),
        x0 = offset.left + dialRadius,
        y0 = offset.top + dialRadius,
        dx = (isTouch ? e.originalEvent.touches[0] : e).pageX - x0,
        dy = (isTouch ? e.originalEvent.touches[0] : e).pageY - y0,
        z = Math.sqrt(dx * dx + dy * dy),
        moved = false;

      // Ignore plate clicks that aren't even close
      if (z < radius - tickRadius || z > radius + tickRadius) {
        return;
      }
      e.preventDefault();
      $(document.body).addClass('lolliclock-moving');

      // Place the canvas to top
      plate.append(self.canvas);

      // Clock
      self.setHand(dx, dy);

      // Mousemove on document
      $(document).off(mousemoveEvent).on(mousemoveEvent, function (e) {
        e.preventDefault();
        var isTouch = /^touch/.test(e.type),
          x = (isTouch ? e.originalEvent.touches[0] : e).pageX - x0,
          y = (isTouch ? e.originalEvent.touches[0] : e).pageY - y0;
        if (!moved && x === dx && y === dy) {
          // Clicking in chrome on windows will trigger a mousemove event
          return;
        }
        moved = true;
        self.setHand(x, y);
      });

      // Mouseup on document
      $(document).off(mouseupEvent).on(mouseupEvent, function (e) {
        e.preventDefault();
        var isTouch = /^touch/.test(e.type),
          x = (isTouch ? e.originalEvent.changedTouches[0] : e).pageX - x0,
          y = (isTouch ? e.originalEvent.changedTouches[0] : e).pageY - y0;
        if (x === dx && y === dy) {
          self.setHand(x, y);
        }
        if (self.currentView === 'hours') {
          self.toggleView('minutes', duration / 2);
        } else if (options.autoclose) {
          self.done();
        }
        plate.prepend(canvas);

        // Reset mouse cursor
        $(document.body).removeClass('lolliclock-moving');

        // Unbind mousemove event
        $(document).off(mousemoveEvent);
        $(document).off(mouseupEvent);
      });
    }

    // Draw clock SVG
    var canvas = popover.find('.lolliclock-canvas');
    var svg = createSvgElement('svg');
    svg.setAttribute('class', 'lolliclock-svg');
    svg.setAttribute('width', diameter);
    svg.setAttribute('height', diameter);
    var g = createSvgElement('g');
    g.setAttribute('transform', 'translate(' + dialRadius + ',' + dialRadius + ')');
    var bearing = createSvgElement('circle');
    bearing.setAttribute('class', 'lolliclock-bearing');
    bearing.setAttribute('cx', 0);
    bearing.setAttribute('cy', 0);
    bearing.setAttribute('r', 1.25);
    var hand = createSvgElement('line');
    hand.setAttribute('x1', 0);
    hand.setAttribute('y1', 0);
    var bg = createSvgElement('circle');
    bg.setAttribute('class', 'lolliclock-canvas-bg');
    bg.setAttribute('r', tickRadius);
    var fg = createSvgElement('circle');
    fg.setAttribute('class', 'lolliclock-canvas-fg');
    fg.setAttribute('r', 3.5);
    g.appendChild(hand);
    g.appendChild(bg);
    g.appendChild(fg);
    g.appendChild(bearing);
    svg.appendChild(g);
    canvas.append(svg);

    this.hand = hand;
    this.bg = bg;
    this.fg = fg;
    this.bearing = bearing;
    this.g = g;
    this.canvas = canvas;

    raiseCallback(this.options.init);
  }

  function raiseCallback(callbackFunction) {
    if (callbackFunction && typeof callbackFunction === "function") {
      callbackFunction();
    }
  }

  // Show or hide popover
  LolliClock.prototype.toggle = function () {
    this[this.isShown ? 'hide' : 'show']();
  };

  LolliClock.prototype.changeAmPm = function (isAmOrPm) {
    if (!!isAmOrPm && isAmOrPm === this.amOrPm) return;
    this.amOrPm = this.amOrPm === 'AM' ? 'PM' : 'AM';
    this.spanAmPm.html(this.amOrPm);
    $(this.amButton[0].childNodes[0]).toggleClass('lolliclock-active-button-background', (this.amOrPm === 'AM'));
    $(this.pmButton[0].childNodes[0]).toggleClass('lolliclock-active-button-background', (this.amOrPm === 'PM'));
    $(this.amButton[0].childNodes[1]).toggleClass('lolliclock-active-button-text', (this.amOrPm === 'AM'));
    $(this.pmButton[0].childNodes[1]).toggleClass('lolliclock-active-button-text', (this.amOrPm === 'PM'));
  };

  // Set popover position, keep it on screen no matter how it's scrolled
  LolliClock.prototype.locate = function () {
    var element = this.element;
    var popover = this.popover;
    var popoverMargin = 8;
    var leftOffset = element.offset().left + (element.outerWidth() - popover.width()) / 2;
    var maxLeft = $(window).width() - popover.width() - popoverMargin;
    var minLeft = popoverMargin;
    var maxTop = $(window).height() + $(window).scrollTop() - popoverMargin - popover.height();
    var minTop = popoverMargin + $(window).scrollTop();
    var topOffset = element.offset().top;
    var styles = {};

    styles.top = topOffset < minTop ? minTop : topOffset > maxTop ? maxTop : topOffset;
    styles.left = leftOffset < minLeft ? minLeft : leftOffset > maxLeft ? maxLeft : leftOffset;
    popover.css(styles);
    popover.show();
  };

  // Show popover
  LolliClock.prototype.show = function () {
    //this.input.trigger('blur');
    if (this.isShown) {
      return;
    }

    raiseCallback(this.options.beforeShow);
    var self = this;

    // Initialize
    if (!this.isAppended) {
      // Append popover to body
      $(document.body).append(this.popover);
      this.isAppended = true;

      // Reset position when resize
      $(window).on('resize.lolliclock' + this.id, function () {
        if (self.isShown) {
          self.locate();
        }
      });

      // Reset position on scroll
      $(window).on('scroll.lolliclock', function () {
        if (self.isShown) {
          self.locate();
        }
      });

      //Add listeners
      this.AmPmButtons.on('click', function (e) {
        self.changeAmPm(e.currentTarget.children[1].innerHTML);
      });
      this.spanMinutes.on('click', function () {
        self.toggleView('minutes');
      });
      this.spanHours.on('click', function () {
        self.toggleView('hours');
      });
      this.spanAmPm.on('click', function () {
        self.changeAmPm();
      });
    }

    // Set position
    self.locate();

    //animate show
    this.plate.addClass('animate');
    this.header.addClass('animate');
    this.popover.addClass('animate');
    this.AmPmButtons.addClass('animate');
    this.spanNewTime.addClass('animate');
    this.spanOldTime.addClass('animate');
    !this.options.autoclose && this.closeButtons.addClass('animate');

    this.plate.on('webkitAnimationEnd animationend MSAnimationEnd oanimationend',
      function () {
        self.plate.removeClass("animate");
        self.header.removeClass("animate");
        self.popover.removeClass("animate");
        self.AmPmButtons.removeClass("animate");
        self.spanNewTime.removeClass("animate");
        self.spanOldTime.removeClass("animate");
        !self.options.autoclose && self.closeButtons.removeClass("animate");
        self.plate.off('webkitAnimationEnd animationend MSAnimationEnd oanimationend');
      }
    );

    //Get the time
    function timeToDate(time) {
      var parts = time.split(':');
      if (parts.length === 2){
        var hours = +parts[0];
        var minAM = parts[1].split(' ');
        if (minAM.length === 2) {
          var mins = minAM[0];
          if (minAM[1] === 'PM') hours += 12;
          return new Date(1970, 1, 1, hours, mins);
        }
      }
      return new Date('x');
    }

    function isValidTime(time) {

      return !isNaN(timeToDate(time).getTime());
    }

    var value;
    var inputValue = this.input.prop('value');
    var defaultValue = this.options.startTime;
    var placeholderValue = this.input.prop('placeholder');

    if (inputValue && isValidTime(inputValue)) {
      value = timeToDate(inputValue);
    } else if (defaultValue === 'now') {
      value = new Date();
    } else if (defaultValue && isValidTime(defaultValue)) {
      value = timeToDate(defaultValue);
    } else if (placeholderValue && isValidTime(placeholderValue)) {
      value = timeToDate(placeholderValue);
    } else {
      value = new Date();
    }
    this.hours = value.getHours() % 12;
    this.minutes = value.getMinutes();
    //purposefully wrong because we change it next line
    this.amOrPm = value.getHours() > 11 ? "AM" : "PM";
    this.changeAmPm();

    // Set time
    self.toggleView('minutes');
    self.toggleView('hours');


    self.isShown = true;

    // Hide when clicking or tabbing on any element except the clock, input
    $(document).on('click.lolliclock.' + this.id + ' focusin.lolliclock.' + this.id, function (e) {
      var target = $(e.target);
      if (target.closest(self.popover).length === 0 &&
        target.closest(self.input).length === 0) {
        self.done();
      }
    });

    // Hide when ESC is pressed
    $(document).on('keyup.lolliclock.' + this.id, function (e) {
      if (e.keyCode === 27) {
        self.hide();
      }
    });
    raiseCallback(this.options.afterShow);
  };

  // Hide popover
  LolliClock.prototype.hide = function () {
    raiseCallback(this.options.beforeHide);

    //animate out
    var self = this;
    self.popover.addClass('animate-out');
    self.plate.addClass("animate-out");
    self.header.addClass("animate-out");
    self.AmPmButtons.addClass("animate-out");
    !self.options.autoclose && self.closeButtons.addClass('animate-out');

    this.popover.on('webkitAnimationEnd animationend MSAnimationEnd oanimationend',
      function () {
        $(self.spanHours[0].childNodes[0]).html('');
        $(self.spanMinutes[0].childNodes[0]).html('');
        self.popover.removeClass("animate-out");
        self.plate.removeClass("animate-out");
        self.header.removeClass("animate-out");
        self.AmPmButtons.removeClass("animate-out");
        !self.options.autoclose && self.closeButtons.removeClass("animate-out");
        self.popover.off('webkitAnimationEnd animationend MSAnimationEnd oanimationend');

        // Unbinding events on document
        $(document).off('click.lolliclock.' + self.id + ' focusin.lolliclock.' + self.id);
        $(document).off('keyup.lolliclock.' + self.id);

        self.popover.hide();
        raiseCallback(self.options.afterHide);
      }
    );

    self.isShown = false;
  };

  // Toggle to hours or minutes view
  LolliClock.prototype.toggleView = function (view, delay) {
    var isHours = view === 'hours';
    var nextView = isHours ? this.hoursView : this.minutesView;
    var hideView = isHours ? this.minutesView : this.hoursView;

    this.currentView = view;

    this.spanHours.toggleClass('lolliclock-primary-text', isHours);
    this.spanMinutes.toggleClass('lolliclock-primary-text', !isHours);

    // Let's make transitions
    hideView.addClass('lolliclock-dial-out');
    nextView.css('visibility', 'visible').removeClass('lolliclock-dial-out');

    // Reset clock hand
    this.resetClock(delay);

    // After transitions ended
    clearTimeout(this.toggleViewTimer);
    this.toggleViewTimer = setTimeout(function () {
      hideView.css('visibility', 'hidden');
    }, duration);

    //Add pointer mouse cursor to show you can click between ticks
    if (isHours) {
      this.plate.off(mousemoveEvent);
    } else {
      var self = this;
      this.plate.on(mousemoveEvent, function (e) {

        var offset = self.plate.offset(),
          x0 = offset.left + dialRadius,
          y0 = offset.top + dialRadius,
          dx = e.pageX - x0,
          dy = e.pageY - y0,
          z = Math.sqrt(dx * dx + dy * dy);
        if (z > radius - tickRadius && z < radius + tickRadius) {
          $(document.body).addClass('lolliclock-clickable');
        } else {
          $(document.body).removeClass('lolliclock-clickable');
        }
      });
    }
  };

  // Reset clock hand
  LolliClock.prototype.resetClock = function (delay) {
    var view = this.currentView,
      value = this[view],
      isHours = view === 'hours',
      unit = Math.PI / (isHours ? 6 : 30),
      radian = value * unit,
      x = Math.sin(radian) * radius,
      y = -Math.cos(radian) * radius,
      self = this;
    if (delay) {
      self.canvas.addClass('lolliclock-canvas-out');
      setTimeout(function () {
        self.canvas.removeClass('lolliclock-canvas-out');
        self.setHand(x, y);
      }, delay);
    } else {
      this.setHand(x, y);
    }
  };

  // Set clock hand to (x, y)
  LolliClock.prototype.setHand = function (x, y) {
    //Keep radians postive from 1 to 2pi
    var radian = Math.atan2(-x, y) + Math.PI;
    var isHours = this.currentView === 'hours';
    var unit = Math.PI / (isHours ? 6 : 30);
    var value;

    // Get the round value
    value = Math.round(radian / unit);
    // Get the round radian
    radian = value * unit;

    // Correct the hours or minutes
    if (isHours) {
      if (value === 0) {
        value = 12;
      }
      this.fg.style.visibility = 'hidden';
    } else {
      var isOnNum = (value % 5 === 0);
      if (isOnNum) {
        this.fg.style.visibility = 'hidden';
      } else {
        this.fg.style.visibility = 'visible';
      }
      if (value === 60) {
        value = 0;
      }
    }

    // Once hours or minutes changed, vibrate the device
    if (this[this.currentView] !== value) {
      if (vibrate && this.options.vibrate) {
        // Do not vibrate too frequently
        if (!this.vibrateTimer) {
          navigator[vibrate](10);
          this.vibrateTimer = setTimeout($.proxy(function () {
            this.vibrateTimer = null;
          }, this), 100);
        }
      }
    }
    //TODO: Keep tens digit static for changing hours
    this[this.currentView] = value;
    function cleanupAnimation($obj) {
      $obj.on('webkitAnimationEnd animationend MSAnimationEnd oanimationend',
        function () {
          $oldTime.html(value); //only needed for -up transitions
          $oldTime.removeClass("old-down old-up");
          $newTime.removeClass("new-down new-up");
          $oldTime.off('webkitAnimationEnd animationend MSAnimationEnd oanimationend');
        });
    }

    var $oldTime;
    var $newTime;
    if (isHours) {
      $oldTime = $(this.spanHours[0].childNodes[0]);
      $newTime = $(this.spanHours[0].childNodes[1]);
    } else {
      $oldTime = $(this.spanMinutes[0].childNodes[0]);
      $newTime = $(this.spanMinutes[0].childNodes[1]);
      value = leadingZero(value);
    }
    cleanupAnimation($oldTime);
    if (value < (+$oldTime.html())) {
      $newTime.html($oldTime.html());
      $oldTime.html(value);
      $newTime.addClass('new-down');
      $oldTime.addClass('old-down');
    } else if (value > (+$oldTime.html()) || !$oldTime.html()) {
      $newTime.html(value);
      $oldTime.addClass('old-up');
      $newTime.addClass('new-up');
    }

    this.g.insertBefore(this.hand, this.bearing);
    this.g.insertBefore(this.bg, this.fg);
    this.bg.setAttribute('class', 'lolliclock-canvas-bg');

    // Set clock hand and others' position
    var cx = Math.sin(radian) * radius,
      cy = -Math.cos(radian) * radius;
    this.hand.setAttribute('x2', Math.sin(radian) * (radius - tickRadius));
    this.hand.setAttribute('y2', -Math.cos(radian) * (radius - tickRadius));
    this.bg.setAttribute('cx', cx);
    this.bg.setAttribute('cy', cy);
    this.fg.setAttribute('cx', cx);
    this.fg.setAttribute('cy', cy);
  };

  // Hours and minutes are selected
  LolliClock.prototype.done = function () {
    raiseCallback(this.options.beforeDone);

    var last = this.input.prop('value');
    var value = this.hours + ':' + leadingZero(this.minutes) + " " + this.amOrPm;
    if (value !== last) {
      this.input.prop('value', value);
      this.input.trigger('input');
      this.input.trigger('change');
    }
    this.hide();
  };

  // Remove lolliclock from input
  LolliClock.prototype.remove = function () {
    this.element.removeData('lolliclock');
    this.input.off('focus.lolliclock click.lolliclock');
    if (this.isShown) {
      this.hide();
    }
    if (this.isAppended) {
      $(window).off('resize.lolliclock' + this.id);
      $(window).off('scroll.lolliclock' + this.id);
      this.popover.remove();
    }
  };
  // Extends $.fn.lolliclock
  $.fn.lolliclock = function (option) {
    var args = Array.prototype.slice.call(arguments, 1);
    return this.each(function () {
      var $this = $(this),
        data = $this.data('lolliclock');
      if (!data) {
        var options = $.extend({}, LolliClock.DEFAULTS, $this.data(), typeof option == 'object' && option);
        $this.data('lolliclock', new LolliClock($this, options));
      } else {
        // Manual operatsions. show, hide, remove, e.g.
        if (typeof data[option] === 'function') {
          data[option].apply(data, args);
        }
      }
    });
  };
}());