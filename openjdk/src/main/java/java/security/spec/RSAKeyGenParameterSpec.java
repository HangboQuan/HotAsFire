/*
 * Copyright (c) 1999, 2022, Oracle and/or its affiliates. All rights reserved.
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
import java.security.spec.AlgorithmParameterSpec;

/**
 * This class specifies the set of parameters used to generate an RSA
 * key pair.
 *
 * @author Jan Luehe
 *
 * @see java.security.KeyPairGenerator#initialize(java.security.spec.AlgorithmParameterSpec)
 *
 * @since 1.3
 */

public class RSAKeyGenParameterSpec implements AlgorithmParameterSpec {

    private int keysize;
    private BigInteger publicExponent;
    private AlgorithmParameterSpec keyParams;

    /**
     * The public-exponent value F0 = 3.
     */
    public static final BigInteger F0 = BigInteger.valueOf(3);

    /**
     * The public exponent-value F4 = 65537.
     */
    public static final BigInteger F4 = BigInteger.valueOf(65537);

    /**
     * Constructs a new {@code RSAKeyGenParameterSpec} object from the
     * given keysize, public-exponent value, and null key parameters.
     *
     * @param keysize the modulus size (specified in number of bits)
     * @param publicExponent the public exponent
     */
    public RSAKeyGenParameterSpec(int keysize, BigInteger publicExponent) {
        this(keysize, publicExponent, null);
    }

    /**
     * Constructs a new {@code RSAKeyGenParameterSpec} object from the
     * given keysize, public-exponent value, and key parameters.
     *
     * @apiNote This method is defined in Java SE 8 Maintenance Release 3.
     * @param keysize the modulus size (specified in number of bits)
     * @param publicExponent the public exponent
     * @param keyParams the key parameters, may be null
     * @since 8
     */
    public RSAKeyGenParameterSpec(int keysize, BigInteger publicExponent,
            AlgorithmParameterSpec keyParams) {
        this.keysize = keysize;
        this.publicExponent = publicExponent;
        this.keyParams = keyParams;
    }

    /**
     * Returns the keysize.
     *
     * @return the keysize.
     */
    public int getKeysize() {
        return keysize;
    }

    /**
     * Returns the public-exponent value.
     *
     * @return the public-exponent value.
     */
    public BigInteger getPublicExponent() {
        return publicExponent;
    }

    /**
     * Returns the parameters to be associated with key.
     *
     * @apiNote This method is defined in Java SE 8 Maintenance Release 3.
     * @return the associated parameters, may be null if
     *         not present
     * @since 8
     */
    public AlgorithmParameterSpec getKeyParams() {
        return keyParams;
    }
}
