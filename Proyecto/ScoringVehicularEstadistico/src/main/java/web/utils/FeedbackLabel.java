/*
 *  Copyright 2008 Daan, StuQ.nl
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package web.utils;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.Component;
import org.apache.wicket.AttributeModifier;

/**
 * Label displaying feedback messages for FormComponents.
 * <p>
 * You can use this Label to show the error message near the actual FormComponent, instead of in the FeedbackPanel
 * It's safe to remove the FeedbackPanel if you use this class for every FormComponent in your Form.
 * <p>
 * You can use this code under Apache 2.0 license, as long as you retain the copyright messages.
 * 
 * Tested with Wicket 1.3.4
 * @author Daan, StuQ.nl
 */
public class FeedbackLabel extends Label {

    /** Field component holds a reference to the {@link Component} this FeedbackLabel belongs to */
    private FormComponent component;
    /** Field text holds a model of the text to be shown in the FeedbackLabel */
    private IModel text = null;

    /**
     * Call this constructor if you just want to display the FeedbackMessage of the component
     * @param id The non-null id of this component
     * @param component The {@link FormComponent} to show the FeedbackMessage for.
     */
    public FeedbackLabel(String id, FormComponent component) {
        super(id);
        this.component = component;
    }

    /**
     * Call this constructor if you want to display a custom text
     * @param id The non-null id of this component
     * @param component The {@link FormComponent} to show the custom text for.
     * @param text The custom text to show when the FormComponent has a FeedbackMessage
     */
    public FeedbackLabel(String id, FormComponent component, String text) {
        this(id, component, new Model(text));
    }

    /**
     * Call this constructor if you want to display a custom model (for easy i18n)
     * @param id The non-null id of this component
     * @param component The {@link FormComponent} to show the custom model for.
     * @param iModel The custom model to show when the {@link FormComponent} has a FeedbackMessage
     */
    public FeedbackLabel(String id, FormComponent component, IModel iModel) {
        super(id);
        this.component = component;
        this.text=iModel;
    }

    /**
     * Set the content of this FeedbackLabel, depending on if the component has a FeedbackMessage.
     *
     * The HTML class attribute will be filled with the error level of the feedback message. That way, you can easily
     * style different messages differently. Examples:
     *
     * class = "feedbacklabel INFO"
     * class = "feedbacklabel ERROR"
     * class = "feedbacklabel DEBUG"
     * class = "feedbacklabel FATAL"
     *
     *
     * @see Component
     */
    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        this.setDefaultModel(null);
        if(component.getFeedbackMessage()!=null){
            if(this.text!=null){
                this.setDefaultModel(text);
            } else {
                this.setDefaultModel(new Model(component.getFeedbackMessage().getMessage()));
            }

            this.add(new AttributeModifier("class", true, new Model("feedbacklabel " + component.getFeedbackMessage().getLevelAsString())));
        } else {
            this.setDefaultModel(null);
        }
    }
}
