/*
 * This file is part of FastClasspathScanner.
 *
 * Author: Luke Hutchison
 *
 * Hosted at: https://github.com/lukehutch/fast-classpath-scanner
 *
 * --
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Luke Hutchison
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
 * LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO
 * EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.lukehutch.fastclasspathscanner.typesignature;

import java.util.Set;

import io.github.lukehutch.fastclasspathscanner.scanner.ScanResult;
import io.github.lukehutch.fastclasspathscanner.typesignature.TypeUtils.ParseState;

/** A type signature for a base type. */
public class BaseTypeSignature extends TypeSignature {
    /** A base type, such as "int", "float", or "void". */
    private final String baseType;

    /**
     * @param baseType
     *            the base type
     */
    public BaseTypeSignature(final String baseType) {
        this.baseType = baseType;
    }

    /**
     * Get the base type, such as "int", "float", or "void".
     * 
     * @return The base type.
     */
    public String getBaseType() {
        return baseType;
    }

    @Override
    public void getAllReferencedClassNames(final Set<String> classNameListOut) {
    }

    @Override
    public Class<?> instantiate(final ScanResult scanResult) {
        switch (baseType) {
        case "byte":
            return byte.class;
        case "char":
            return char.class;
        case "double":
            return double.class;
        case "float":
            return float.class;
        case "int":
            return int.class;
        case "long":
            return long.class;
        case "short":
            return short.class;
        case "boolean":
            return boolean.class;
        case "void":
            return void.class;
        default:
            throw new RuntimeException("Unknown base type " + baseType);
        }
    }

    @Override
    public int hashCode() {
        return baseType.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof BaseTypeSignature && ((BaseTypeSignature) obj).baseType.equals(this.baseType);
    }

    @Override
    public boolean equalsIgnoringTypeParams(final TypeSignature other) {
        if (!(other instanceof BaseTypeSignature)) {
            return false;
        }
        return baseType.equals(((BaseTypeSignature) other).baseType);
    }

    @Override
    public String toString() {
        return baseType;
    }

    /** Parse a base type. */
    static BaseTypeSignature parse(final ParseState parseState) {
        switch (parseState.peek()) {
        case 'B':
            parseState.next();
            return new BaseTypeSignature("byte");
        case 'C':
            parseState.next();
            return new BaseTypeSignature("char");
        case 'D':
            parseState.next();
            return new BaseTypeSignature("double");
        case 'F':
            parseState.next();
            return new BaseTypeSignature("float");
        case 'I':
            parseState.next();
            return new BaseTypeSignature("int");
        case 'J':
            parseState.next();
            return new BaseTypeSignature("long");
        case 'S':
            parseState.next();
            return new BaseTypeSignature("short");
        case 'Z':
            parseState.next();
            return new BaseTypeSignature("boolean");
        case 'V':
            parseState.next();
            return new BaseTypeSignature("void");
        default:
            return null;
        }
    }
}