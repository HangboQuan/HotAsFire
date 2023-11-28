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
 * This class specifies an RSA public key.
 *
 * @author Jan Luehe
 *
 *
 * @see java.security.Key
 * @see java.security.KeyFactory
 * @see KeySpec
 * @see X509EncodedKeySpec
 * @see RSAPrivateKeySpec
 * @see RSAPrivateCrtKeySpec
 */

public class RSAPublicKeySpec implements KeySpec {

    private final BigInteger modulus;
    private final BigInteger publicExponent;
    private final AlgorithmParameterSpec params;

    /**
     * Creates a new RSAPublicKeySpec.
     *
     * @param modulus the modulus
     * @param publicExponent the public exponent
     */
    public RSAPublicKeySpec(BigInteger modulus, BigInteger publicExponent) {
        this(modulus, publicExponent, null);
    }

    /**
     * Creates a new RSAPublicKeySpec with additional key parameters.
     *
     * @apiNote This method is defined in Java SE 8 Maintenance Release 3.
     * @param modulus the modulus
     * @param publicExponent the public exponent
     * @param params the parameters associated with this key, may be null
     * @since 8
     */
    public RSAPublicKeySpec(BigInteger modulus, BigInteger publicExponent,
            AlgorithmParameterSpec params) {
        this.modulus = modulus;
        this.publicExponent = publicExponent;
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
     * Returns the public exponent.
     *
     * @return the public exponent
     */
    public BigInteger getPublicExponent() {
        return this.publicExponent;
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
