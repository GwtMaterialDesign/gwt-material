function AppInstaller(fallback) {

    window.install.canPrompt(fallback)
        .then(function () {

        }).catch(function (error) {
            fallback.call(error);
        }
    );

    this.prompt = function () {
        window.install.prompt().then(function (outcome) {
        }).catch(function (error) {
            fallback.call(error);
        });
    }

    this.isLaunched = function (displayMode) {
        return window.matchMedia('(display-mode: ' + displayMode + ')').matches;
    }
}

(function () {
    var deferredInstall;
    var promptTriggered = false;
    // The resolve function that will be called when we know we can prompt.
    var canPromptPromiseResolved;
    var canPromptPromise = new Promise(function (resolve, reject) {
        // The resolve will be called later when we know the prompt has been shown.
        // We might want to reject after a timeout of a couple of seconds.
        canPromptPromiseResolved = resolve;
    });

    window.addEventListener('beforeinstallprompt', function (e) {
        promptTriggered = true;
        // Stop it doing what it needs to do;
        e.preventDefault();
        deferredInstall = e;
        // Resolve the promise, to say that we know we can prompt.
        canPromptPromiseResolved();
        return false;
    });

    var install = {};

    Object.defineProperty(install, 'isAvailable', {
        get: function () {
            return promptTriggered;
        }
    });

    install.canPrompt = function () {
        return canPromptPromise;
    };

    install.prompt = function () {
        return new Promise(function (resolve, reject) {
            if (promptTriggered === false) {
                // There can be a whole host or reasons, we should determine them
                reject('User Agent decided not to prompt');
            }
            ;

            deferredInstall.prompt().then(function () {
                return deferredInstall.userChoice
            }).then(function (choice) {
                resolve(choice.outcome);
            }).catch(function (reason) {
                reject(reason);
            });
        });
    };

    window.install = install;
})();