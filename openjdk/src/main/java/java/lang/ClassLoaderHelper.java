/*
 * Copyright (c) 2012, 2021, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package java.lang;

import java.io.File;

class ClassLoaderHelper {

    private ClassLoaderHelper() {}

    /**
     * Returns true if loading a native library only if
     * it's present on the file system.
     */
    static boolean loadLibraryOnlyIfPresent() {
        return true;
    }

    /**
     * Returns an alternate path name for the given file
     * such that if the original pathname did not exist, then the
     * file may be located at the alternate location.
     * For most platforms, this behavior is not supported and returns null.
     */
    static File mapAlternativeName(File lib) {
        return null;
    }
}
