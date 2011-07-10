/*
 * Copyright (C) 2011 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package am.ik.support.morphia;

import java.beans.PropertyEditorSupport;

import org.bson.types.ObjectId;
import org.springframework.util.StringUtils;

public class ObjectIdEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasLength(text) || "_empty".equals(text)) {
            setValue(null);
        } else {
            setValue(new ObjectId(text));
        }
    }

    @Override
    public String getAsText() {
        ObjectId id = (ObjectId) getValue();
        return id != null ? id.toString() : null;
    }
}
