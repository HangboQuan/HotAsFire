/*
 * Copyright (c) 1998, 2022, Oracle and/or its affiliates. All rights reserved.
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

package java.security.spec;

import java.math.BigInteger;

/**
 * This class specifies an RSA private key.
 *
 * @author Jan Luehe
 *
 *
 * @see java.security.Key
 * @see java.security.KeyFactory
 * @see KeySpec
 * @see PKCS8EncodedKeySpec
 * @see RSAPublicKeySpec
 * @see RSAPrivateCrtKeySpec
 */

public class RSAPrivateKeySpec implements KeySpec {

    private final BigInteger modulus;
    private final BigInteger privateExponent;
    private final AlgorithmParameterSpec params;

    /**
     * Creates a new RSAPrivateKeySpec.
     *
     * @param modulus the modulus
     * @param privateExponent the private exponent
     */
    public RSAPrivateKeySpec(BigInteger modulus, BigInteger privateExponent) {
        this(modulus, privateExponent, null);
    }

    /**
     * Creates a new RSAPrivateKeySpec with additional key parameters.
     *
     * @apiNote This method is defined in Java SE 8 Maintenance Release 3.
     * @param modulus the modulus
     * @param privateExponent the private exponent
     * @param params the parameters associated with this key, may be null
     * @since 8
     */
    public RSAPrivateKeySpec(BigInteger modulus, BigInteger privateExponent,
            AlgorithmParameterSpec params) {
        this.modulus = modulus;
        this.privateExponent = privateExponent;
        this.params = params;
    }

    /**
     * Returns the modulus.
     *
     * @return the modulus
     */
    public BigInteger getModulus() {
        return this.modulus;
    }

    /**
     * Returns the private exponent.
     *
     * @return the private exponent
     */
    public BigInteger getPrivateExponent() {
        return this.privateExponent;
    }

    /**
     * Returns the parameters associated with this key, may be null if not
     * present.
     *
     * @apiNote This method is defined in Java SE 8 Maintenance Release 3.
     * @return the parameters associated with this key
     * @since 8
     */
    public AlgorithmParameterSpec getParams() {
        return this.params;
    }
}
