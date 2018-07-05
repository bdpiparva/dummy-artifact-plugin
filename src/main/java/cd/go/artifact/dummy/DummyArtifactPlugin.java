/*
 * Copyright 2018 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.artifact.dummy;

import cd.go.artifact.dummy.model.ArtifactStore;
import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.GoPlugin;
import com.thoughtworks.go.plugin.api.GoPluginIdentifier;
import com.thoughtworks.go.plugin.api.annotation.Extension;
import com.thoughtworks.go.plugin.api.exceptions.UnhandledRequestTypeException;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import static cd.go.artifact.dummy.ResourceReader.read;
import static cd.go.artifact.dummy.model.ArtifactStore.artifactStoreMetadata;
import static java.util.Collections.singletonList;
import static java.util.Collections.singletonMap;

@Extension
public class DummyArtifactPlugin implements GoPlugin {
    public static final Gson GSON = new Gson();
    private GoApplicationAccessor goApplicationAccessor;

    @Override
    public void initializeGoApplicationAccessor(GoApplicationAccessor goApplicationAccessor) {
        this.goApplicationAccessor = goApplicationAccessor;
    }

    @Override
    public GoPluginApiResponse handle(GoPluginApiRequest request) throws UnhandledRequestTypeException {
        final RequestFromServer requestFromServer = RequestFromServer.from(request.requestName());
        switch (requestFromServer) {
            case REQUEST_GET_CAPABILITIES:
                return DefaultGoPluginApiResponse.success("{}");
            case REQUEST_STORE_CONFIG_METADATA:
                return DefaultGoPluginApiResponse.success(artifactStoreMetadata());
            case REQUEST_STORE_CONFIG_VIEW:
                final String template = GSON.toJson(singletonMap("template", read("/artifact-store.template.html")));
                return DefaultGoPluginApiResponse.success(template);
            case REQUEST_STORE_CONFIG_VALIDATE:
                return DefaultGoPluginApiResponse.success(ArtifactStore.from(request.requestBody()).validate().toJSON());
            case REQUEST_PUBLISH_ARTIFACT_METADATA:
                break;
            case REQUEST_PUBLISH_ARTIFACT_VIEW:
                break;
            case REQUEST_PUBLISH_ARTIFACT_VALIDATE:
                break;
            case REQUEST_FETCH_ARTIFACT_METADATA:
                break;
            case REQUEST_FETCH_ARTIFACT_VIEW:
                break;
            case REQUEST_FETCH_ARTIFACT_VALIDATE:
                break;
            case REQUEST_PUBLISH_ARTIFACT:
                break;
            case REQUEST_FETCH_ARTIFACT:
                break;
            case REQUEST_GET_PLUGIN_ICON:
                break;
        }

        throw new UnhandledRequestTypeException("fooo");
    }

    @Override
    public GoPluginIdentifier pluginIdentifier() {
        return new GoPluginIdentifier("artifact", singletonList("1.0"));
    }
}
