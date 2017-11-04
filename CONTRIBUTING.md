# Contributing

## Please Be Friendly

We strongly encourage everyone participating in gwt-material development to show courtesy and respect to others. Of course, being courteous should not prevent us from constructively disagreeing with each other. But if you are enumerating 42 technical reasons against a particular proposal, please don't make the criticism worse by ridiculing the person who proposed it. State your technical disagreement freely, but respect the person you disagree with. That person may be a great learner who soon will be making the best proposals of us all.

Respectful also doesn't mean "serious". Web application development may be hard work, but it's also a lot of fun! Being lighthearted and playful is welcome. Let's enjoy being one of the friendliest communities in the whole open source movement.

## Code Style

To keep the source consistent, readable, diffable and easy to merge, all patches will be expected to conform to the style outlined here. To keep things as simple as possible. If you're using Eclipse, we strongly recommend that you install and enable the [http://eclipse-cs.sourceforge.net/ Eclipse Checkstyle plugin] that is used by the provided eclipse configuration.

In general, the gwt-material style is based on the [http://java.sun.com/docs/codeconv/html/CodeConvTOC.doc.html standard Java conventions].

For the record, we know that coding style is often a controversial topic. We acknowledge that plenty of great approaches exist out there. We're simply trying to pick one that is at least somewhat consistent with Sun's Java coding conventions, to codify it well, and stick to it.

## Indentation

We use 4-space indents for blocks. No tabs at all, anywhere.

We use 4-space indents after line wraps, including function calls and assignments. For example, this is correct (4 spaces after the newline):
```
LineWrapExample i =
    new LineWrapExample();
```

and this is not correct (2 spaces after the newline):
```
LineWrapExample ex =
  new LineWrapExample();
```

## Comments and Javadoc

Every file should have an Apache license header at the top underneath the package. A package statement and import statements should follow, each block separated by a blank line. Next is the class or interface declaration. In the Javadoc comments, describe what the class or interface does.

```java
/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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
package ...

import ...
```

## Naming Conventions

Some examples of class naming:
```
Good	        Bad
XmlHttpRequest	XMLHTTPRequest
getCustomerId	getCustomerID
```
This style rule also applies when an acronym or abbreviation is the entire name:

```
Good	    Bad
class Html	class HTML
String url;	String URL;
long id;	long ID;
```

TODO - complete this

## Updating your Fork
First make sure you have an upstream remote:
```
git remote add https://github.com/GwtMaterialDesign/gwt-material.git upstream
```

Next, fetch from the upstream remote:
```
git fetch upstream
```

Then when changes are made to the official repository, make this call:
```
git pull --rebase upstream master # Note that this pulls from master branch (can be changed)
```
