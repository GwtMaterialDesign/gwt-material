/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.ui.html;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.VideoElement;
import gwt.material.design.client.base.MaterialWidget;

public class Video extends MaterialWidget {

    protected VideoElement element;
    protected SourceVideo[] sources;
    protected boolean loop = true;
    protected boolean autoplay = true;
    protected boolean muted = true;

    protected String type = SourceVideo.Type.WEBM.name();
    protected String src;
    protected String alt;

    public Video() {
        super(Document.get().createVideoElement());
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        element = getElement().cast();
        element.setLoop(loop);
        element.setAutoplay(autoplay);
        element.setMuted(muted);
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isAutoplay() {
        return autoplay;
    }

    public void setAutoplay(boolean autoplay) {
        this.autoplay = autoplay;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public SourceVideo[] getSources() {
        return sources;
    }

    public void setSources(SourceVideo[] sources) {
        this.sources = sources;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
