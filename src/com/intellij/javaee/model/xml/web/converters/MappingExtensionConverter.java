/*
 * Copyright 2000-2007 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intellij.javaee.model.xml.web.converters;

import com.intellij.util.xml.ConvertContext;
import com.intellij.util.xml.ResolvingConverter;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Collections;

/**
 * @author Dmitry Avdeev
 */
public class MappingExtensionConverter extends ResolvingConverter.StringConverter {

  @NonNls private final static List<String> STANDARD_EXTENSIONS = Arrays.asList(
   "hqx","cpt",  "doc","bin", "dms", "lha", "lzh", "exe", "class","oda", "pdf", "ai", "eps", "ps","ppt","rtf", "bcpio",
   "vcd", "cpio", "csh", "dcr", "dir", "dxr", "dvi", "gtar", "gz", "hdf", "cgi", "skp", "skd", "skt", "skm",
   "latex", "mif","nc", "cdf","sh","shar", "sit", "sv4cpio",
   "sv4crc", "tar",  "tcl","tex",  "texinfo", "texi","t", "tr", "roff","man", "me","ms", "ustar", "src",  "zip",
      "au", "snd",   "mpga", "mp2", "aif", "aiff", "aifc", "ram","rpm", "ra", "wav", "pdb", "xyz", "gif",  "ief",
      "jpeg", "jpg", "jpe",  "png",  "tiff", "tif", "ras", "pnm", "pbm", "pgm","ppm","rgb", "xbm", "xpm", "xwd",

   "html", "htm","txt", "rtx", "tsv", "etx", "sgml", "sgm", "mpeg", "mpg", "mpe", "qt", "mov", "avi", "movie", "ice", "wrl", "vrml"
   );

  static {
    Collections.sort(STANDARD_EXTENSIONS);
  }

  @NotNull
  public Collection<? extends String> getVariants(final ConvertContext context) {
    return STANDARD_EXTENSIONS;
  }
}
