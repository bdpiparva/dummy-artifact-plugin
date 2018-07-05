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

package cd.go.artifact.dummy.model;

import cd.go.artifact.dummy.config.Field;
import cd.go.artifact.dummy.config.Metadata;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

import static cd.go.artifact.dummy.DummyArtifactPlugin.GSON;

public class ArtifactConfig {
    @SerializedName("Source")
    private String directory;

    @SerializedName("Destination")
    private String destination;

    public String getDirectory() {
        return directory;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArtifactConfig)) return false;

        ArtifactConfig that = (ArtifactConfig) o;

        return directory != null ? directory.equals(that.directory) : that.directory == null;
    }

    @Override
    public int hashCode() {
        return directory != null ? directory.hashCode() : 0;
    }

    public static String artifactConfigMetadata() {
        return GSON.toJson(Arrays.asList(
                new Field("Source", new Metadata(true, false)),
                new Field("Destination", new Metadata(true, false))
        ));
    }
}
