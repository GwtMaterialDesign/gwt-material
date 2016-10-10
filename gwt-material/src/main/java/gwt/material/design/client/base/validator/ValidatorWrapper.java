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
package gwt.material.design.client.base.validator;

/**
 * Wraps a validator in order to provide sorting capability.
 * <p>
 * We sort based on priority first, then insertion order. The hashCode and equals function should prevent a
 * set from containing 2 validators of the same type.
 *
 * @author Steven Jardine
 */
public class ValidatorWrapper<T> implements Comparable<ValidatorWrapper<T>> {

    private final Integer insertionOrder;

    private final String name;

    private final Integer priority;

    private final Validator<T> validator;

    /**
     * Constructor.
     *
     * @param validator      the validator
     * @param insertionOrder the insertion order
     */
    public ValidatorWrapper(Validator<T> validator, int insertionOrder) {
        this.validator = validator;
        this.insertionOrder = insertionOrder;
        this.name = validator.getClass().getName();
        this.priority = validator.getPriority();
    }

    @Override
    public int compareTo(ValidatorWrapper<T> other) {
        if (this == other || getName().equals(other.getName())) {
            return 0;
        }
        int result = getPriority().compareTo(other.getPriority());
        if (result == 0) {
            result = getInsertionOrder().compareTo(other.getInsertionOrder());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        ValidatorWrapper<?> other = (ValidatorWrapper<?>) obj;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }

    /**
     * @return the insertionOrder
     */
    public Integer getInsertionOrder() {
        return insertionOrder;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @return the validator
     */
    public Validator<T> getValidator() {
        return validator;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
}
