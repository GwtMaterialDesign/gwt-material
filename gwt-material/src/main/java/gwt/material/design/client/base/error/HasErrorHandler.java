/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2015 GwtBootstrap3
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
package gwt.material.design.client.base.error;

/**
 * Has an error handler.
 *
 * @author Steven Jardine
 */
public interface HasErrorHandler {

    /**
     * Gets the error handler.
     *
     * @return the error handler
     */
    ErrorHandler getErrorHandler();

    /**
     * Sets the error handler.
     *
     * @param errorHandler the new error handler
     */
    void setErrorHandler(ErrorHandler errorHandler);

    /**
     * Gets the error handler type.
     *
     * @return the error handler type
     */
    ErrorHandlerType getErrorHandlerType();

    /**
     * Sets the error handler type.
     *
     * @param errorHandlerType the new error handler type
     */
    void setErrorHandlerType(ErrorHandlerType errorHandlerType);
}
