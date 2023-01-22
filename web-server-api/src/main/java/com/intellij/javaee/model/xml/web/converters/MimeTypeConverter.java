/*
 * Copyright 2000-2013 JetBrains s.r.o.
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

import consulo.xml.util.xml.ConvertContext;
import consulo.xml.util.xml.ResolvingConverter;
import org.jetbrains.annotations.NonNls;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Dmitry Avdeev
 */
public class MimeTypeConverter extends ResolvingConverter.StringConverter {

  @NonNls private final static List<String> STANDARD_TYPES = Arrays.asList(
    "application/activemessage",
    "application/andrew-inset",
    "application/applefile",
    "application/atomicmail",
    "application/dca-rft",
    "application/dec-dx",
    "application/json",
    "application/mac-binhex40",
    "application/mac-compactpro",
    "application/macwriteii",
    "application/msword",
    "application/news-message-id",
    "application/news-transmission",
    "application/octet-stream",
    "application/oda",
    "application/pdf",
    "application/postscript",
    "application/powerpoint",
    "application/remote-printing",
    "application/rtf",
    "application/slate",
    "application/wita",
    "application/wordperfect5.1",
    "application/x-bcpio",
    "application/x-cdlink",
    "application/x-compress",
    "application/x-cpio",
    "application/x-csh",
    "application/x-director",
    "application/x-dvi",
    "application/x-gtar",
    "application/x-gzip",
    "application/x-hdf",
    "application/x-httpd-cgi",
    "application/x-koan",
    "application/x-latex",
    "application/x-mif",
    "application/x-netcdf",
    "application/x-sh",
    "application/x-shar",
    "application/x-stuffit",
    "application/x-sv4cpio",
    "application/x-sv4crc",
    "application/x-tar",
    "application/x-tcl",
    "application/x-tex",
    "application/x-texinfo",
    "application/x-troff",
    "application/x-troff-man",
    "application/x-troff-me",
    "application/x-troff-ms",
    "application/x-ustar",
    "application/x-wais-source",
    "application/zip",
    "audio/basic",
    "audio/mpeg",
    "audio/x-aiff",
    "audio/x-pn-realaudio",
    "audio/x-pn-realaudio-plugin",
    "audio/x-realaudio",
    "audio/x-wav",
    "chemical/x-pdb",
    "image/gif",
    "image/ief",
    "image/jpeg",
    "image/png",
    "image/tiff",
    "image/x-cmu-raster",
    "image/x-portable-anymap",
    "image/x-portable-bitmap",
    "image/x-portable-graymap",
    "image/x-portable-pixmap",
    "image/x-rgb",
    "image/x-xbitmap",
    "image/x-xpixmap",
    "image/x-xwindowdump",
    "message/external-body",
    "message/news",
    "message/partial",
    "message/rfc822",
    "multipart/alternative",
    "multipart/appledouble",
    "multipart/digest",
    "multipart/mixed",
    "multipart/parallel",
    "text/html",
    "text/plain",
    "text/richtext",
    "text/tab-separated-values",
    "text/x-setext",
    "text/x-sgml",
    "video/mpeg",
    "video/quicktime",
    "video/x-msvideo",
    "video/x-sgi-movie",
    "x-conference/x-cooltalk",
    "x-world/x-vrml"
  );

  static {
    Collections.sort(STANDARD_TYPES);
  }

  @Nonnull
  public Collection<? extends String> getVariants(final ConvertContext context) {
    return STANDARD_TYPES;
  }
}
