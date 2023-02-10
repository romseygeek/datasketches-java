/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.datasketches.theta;

import org.apache.datasketches.common.SketchesArgumentException;

public class BitPacking {

  public static void packBits(long value, int bits, byte[] buffer, int bufOffset, int bitOffset) {
    if (bitOffset > 0) {
      final int chunkBits = 8 - bitOffset;
      final int mask = (1 << chunkBits) - 1;
      if (bits < chunkBits) {
        buffer[bufOffset] |= (value << (chunkBits - bits)) & mask;
        return;
      }
      buffer[bufOffset++] |= (value >>> (bits - chunkBits)) & mask;
      bits -= chunkBits;
    }
    while (bits >= 8) {
      buffer[bufOffset++] = (byte)(value >>> (bits - 8));
      bits -= 8;
    }
    if (bits > 0) {
      buffer[bufOffset] = (byte)(value << (8 - bits));
    }
  }

  public static void unpackBits(long[] value, int index, int bits, byte[] buffer, int bufOffset, int bitOffset) {
    final int availBits = 8 - bitOffset;
    final int chunkBits = availBits <= bits ? availBits : bits;
    final int mask = (1 << chunkBits) - 1;
    value[index] = (buffer[bufOffset] >>> (availBits - chunkBits)) & mask;
    bufOffset += availBits == chunkBits ? 1 : 0;
    bits -= chunkBits;
    while (bits >= 8) {
      value[index] <<= 8;
      value[index] |= (Byte.toUnsignedLong(buffer[bufOffset++]));
      bits -= 8;
    }
    if (bits > 0) {
      value[index] <<= bits;
      value[index] |= Byte.toUnsignedLong(buffer[bufOffset]) >>> (8 - bits);
    }
  }

  // pack given number of bits from a block of 8 64-bit values into bytes
  // we don't need 0 and 64 bits
  // we assume that higher bits (which we are not packing) are zeros
  // this assumption allows to avoid masking operations

  static void packBitsBlock8(long[] values, int i, byte[] buf, int off, int bits) {
    switch (bits) {
      case 1: packBits1(values, i, buf, off); break;
      case 2: packBits2(values, i, buf, off); break;
      case 3: packBits3(values, i, buf, off); break;
      case 4: packBits4(values, i, buf, off); break;
      case 5: packBits5(values, i, buf, off); break;
      case 6: packBits6(values, i, buf, off); break;
      case 7: packBits7(values, i, buf, off); break;
      case 8: packBits8(values, i, buf, off); break;
      case 9: packBits9(values, i, buf, off); break;
      case 10: packBits10(values, i, buf, off); break;
      case 11: packBits11(values, i, buf, off); break;
      case 12: packBits12(values, i, buf, off); break;
      case 13: packBits13(values, i, buf, off); break;
      case 14: packBits14(values, i, buf, off); break;
      case 15: packBits15(values, i, buf, off); break;
      case 16: packBits16(values, i, buf, off); break;
      case 17: packBits17(values, i, buf, off); break;
      case 18: packBits18(values, i, buf, off); break;
      case 19: packBits19(values, i, buf, off); break;
      case 20: packBits20(values, i, buf, off); break;
      case 21: packBits21(values, i, buf, off); break;
      case 22: packBits22(values, i, buf, off); break;
      case 23: packBits23(values, i, buf, off); break;
      case 24: packBits24(values, i, buf, off); break;
      case 25: packBits25(values, i, buf, off); break;
      case 26: packBits26(values, i, buf, off); break;
      case 27: packBits27(values, i, buf, off); break;
      case 28: packBits28(values, i, buf, off); break;
      case 29: packBits29(values, i, buf, off); break;
      case 30: packBits30(values, i, buf, off); break;
      case 31: packBits31(values, i, buf, off); break;
      case 32: packBits32(values, i, buf, off); break;
      case 33: packBits33(values, i, buf, off); break;
      case 34: packBits34(values, i, buf, off); break;
      case 35: packBits35(values, i, buf, off); break;
      case 36: packBits36(values, i, buf, off); break;
      case 37: packBits37(values, i, buf, off); break;
      case 38: packBits38(values, i, buf, off); break;
      case 39: packBits39(values, i, buf, off); break;
      case 40: packBits40(values, i, buf, off); break;
      case 41: packBits41(values, i, buf, off); break;
      case 42: packBits42(values, i, buf, off); break;
      case 43: packBits43(values, i, buf, off); break;
      case 44: packBits44(values, i, buf, off); break;
      case 45: packBits45(values, i, buf, off); break;
      case 46: packBits46(values, i, buf, off); break;
      case 47: packBits47(values, i, buf, off); break;
      case 48: packBits48(values, i, buf, off); break;
      case 49: packBits49(values, i, buf, off); break;
      case 50: packBits50(values, i, buf, off); break;
      case 51: packBits51(values, i, buf, off); break;
      case 52: packBits52(values, i, buf, off); break;
      case 53: packBits53(values, i, buf, off); break;
      case 54: packBits54(values, i, buf, off); break;
      case 55: packBits55(values, i, buf, off); break;
      case 56: packBits56(values, i, buf, off); break;
      case 57: packBits57(values, i, buf, off); break;
      case 58: packBits58(values, i, buf, off); break;
      case 59: packBits59(values, i, buf, off); break;
      case 60: packBits60(values, i, buf, off); break;
      case 61: packBits61(values, i, buf, off); break;
      case 62: packBits62(values, i, buf, off); break;
      case 63: packBits63(values, i, buf, off); break;
      default: throw new SketchesArgumentException("wrong number of bits " + bits);
    }
  }

  static void unpackBitsBlock8(long[] values, int i, byte[] buf, int off, int bits) {
    switch (bits) {
      case 1: unpackBits1(values, i, buf, off); break;
      case 2: unpackBits2(values, i, buf, off); break;
      case 3: unpackBits3(values, i, buf, off); break;
      case 4: unpackBits4(values, i, buf, off); break;
      case 5: unpackBits5(values, i, buf, off); break;
      case 6: unpackBits6(values, i, buf, off); break;
      case 7: unpackBits7(values, i, buf, off); break;
      case 8: unpackBits8(values, i, buf, off); break;
      case 9: unpackBits9(values, i, buf, off); break;
      case 10: unpackBits10(values, i, buf, off); break;
      case 11: unpackBits11(values, i, buf, off); break;
      case 12: unpackBits12(values, i, buf, off); break;
      case 13: unpackBits13(values, i, buf, off); break;
      case 14: unpackBits14(values, i, buf, off); break;
      case 15: unpackBits15(values, i, buf, off); break;
      case 16: unpackBits16(values, i, buf, off); break;
      case 17: unpackBits17(values, i, buf, off); break;
      case 18: unpackBits18(values, i, buf, off); break;
      case 19: unpackBits19(values, i, buf, off); break;
      case 20: unpackBits20(values, i, buf, off); break;
      case 21: unpackBits21(values, i, buf, off); break;
      case 22: unpackBits22(values, i, buf, off); break;
      case 23: unpackBits23(values, i, buf, off); break;
      case 24: unpackBits24(values, i, buf, off); break;
      case 25: unpackBits25(values, i, buf, off); break;
      case 26: unpackBits26(values, i, buf, off); break;
      case 27: unpackBits27(values, i, buf, off); break;
      case 28: unpackBits28(values, i, buf, off); break;
      case 29: unpackBits29(values, i, buf, off); break;
      case 30: unpackBits30(values, i, buf, off); break;
      case 31: unpackBits31(values, i, buf, off); break;
      case 32: unpackBits32(values, i, buf, off); break;
      case 33: unpackBits33(values, i, buf, off); break;
      case 34: unpackBits34(values, i, buf, off); break;
      case 35: unpackBits35(values, i, buf, off); break;
      case 36: unpackBits36(values, i, buf, off); break;
      case 37: unpackBits37(values, i, buf, off); break;
      case 38: unpackBits38(values, i, buf, off); break;
      case 39: unpackBits39(values, i, buf, off); break;
      case 40: unpackBits40(values, i, buf, off); break;
      case 41: unpackBits41(values, i, buf, off); break;
      case 42: unpackBits42(values, i, buf, off); break;
      case 43: unpackBits43(values, i, buf, off); break;
      case 44: unpackBits44(values, i, buf, off); break;
      case 45: unpackBits45(values, i, buf, off); break;
      case 46: unpackBits46(values, i, buf, off); break;
      case 47: unpackBits47(values, i, buf, off); break;
      case 48: unpackBits48(values, i, buf, off); break;
      case 49: unpackBits49(values, i, buf, off); break;
      case 50: unpackBits50(values, i, buf, off); break;
      case 51: unpackBits51(values, i, buf, off); break;
      case 52: unpackBits52(values, i, buf, off); break;
      case 53: unpackBits53(values, i, buf, off); break;
      case 54: unpackBits54(values, i, buf, off); break;
      case 55: unpackBits55(values, i, buf, off); break;
      case 56: unpackBits56(values, i, buf, off); break;
      case 57: unpackBits57(values, i, buf, off); break;
      case 58: unpackBits58(values, i, buf, off); break;
      case 59: unpackBits59(values, i, buf, off); break;
      case 60: unpackBits60(values, i, buf, off); break;
      case 61: unpackBits61(values, i, buf, off); break;
      case 62: unpackBits62(values, i, buf, off); break;
      case 63: unpackBits63(values, i, buf, off); break;
      default: throw new SketchesArgumentException("wrong number of bits " + bits);
    }
  }

  static void packBits1(long[] values, int i, byte[] buf, int off) {
    buf[off] = (byte) (values[i + 0] << 7);
    buf[off] |= values[i + 1] << 6;
    buf[off] |= values[i + 2] << 5;
    buf[off] |= values[i + 3] << 4;
    buf[off] |= values[i + 4] << 3;
    buf[off] |= values[i + 5] << 2;
    buf[off] |= values[i + 6] << 1;
    buf[off] |= values[i + 7];
  }

  static void packBits2(long[] values, int i, byte[] buf, int off) {
    buf[off] = (byte) (values[0] << 6);
    buf[off] |= values[1] << 4;
    buf[off] |= values[2] << 2;
    buf[off++] |= values[3];

    buf[off] = (byte) (values[4] << 6);
    buf[off] |= values[5] << 4;
    buf[off] |= values[6] << 2;
    buf[off] |= values[7];
  }

  static void packBits3(long[] values, int i, byte[] buf, int off) {
    buf[off] = (byte) (values[0] << 5);
    buf[off] |= values[1] << 2;
    buf[off++] |= values[2] >>> 1;

    buf[off] = (byte) (values[2] << 7);
    buf[off] |= values[3] << 4;
    buf[off] |= values[4] << 1;
    buf[off++] |= values[5] >>> 2;

    buf[off] = (byte) (values[5] << 6);
    buf[off] |= values[6] << 3;
    buf[off] |= values[7];
  }

  static void packBits4(long[] values, int i, byte[] buf, int off) {
    buf[off] = (byte) (values[0] << 4);
    buf[off++] |= values[1];

    buf[off] = (byte) (values[2] << 4);
    buf[off++] |= values[3];

    buf[off] = (byte) (values[4] << 4);
    buf[off++] |= values[5];

    buf[off] = (byte) (values[6] << 4);
    buf[off] |= values[7];
  }

  static void packBits5(long[] values, int i, byte[] buf, int off) {
    buf[off] = (byte) (values[0] << 3);
    buf[off++] |= values[1] >>> 2;

    buf[off] = (byte) (values[1] << 6);
    buf[off] |= values[2] << 1;
    buf[off++] |= values[3] >>> 4;

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 1;

    buf[off] = (byte) (values[4] << 7);
    buf[off] |= values[5] << 2;
    buf[off++] |= values[6] >>> 3;

    buf[off] = (byte) (values[6] << 5);
    buf[off] |= values[7];
  }

  static void packBits6(long[] values, int i, byte[] buf, int off) {
    buf[off] = (byte) (values[0] << 2);
    buf[off++] |= values[1] >>> 4;

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 2;

    buf[off] = (byte) (values[2] << 6);
    buf[off++] |= values[3];

    buf[off] = (byte) (values[4] << 2);
    buf[off++] |= values[5] >>> 4;

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 2;

    buf[off] = (byte) (values[6] << 6);
    buf[off] |= values[7];
  }

  static void packBits7(long[] values, int i, byte[] buf, int off) {
    buf[off] = (byte) (values[0] << 1);
    buf[off++] |= values[1] >>> 6;

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 5;

    buf[off] = (byte) (values[2] << 3);
    buf[off++] |= values[3] >>> 4;

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 3;

    buf[off] = (byte) (values[4] << 5);
    buf[off++] |= values[5] >>> 2;

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 1;

    buf[off] = (byte) (values[6] << 7);
    buf[off] |= values[7];
  }

  static void packBits8(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0]);
    buf[off++] = (byte) (values[1]);
    buf[off++] = (byte) (values[2]);
    buf[off++] = (byte) (values[3]);
    buf[off++] = (byte) (values[4]);
    buf[off++] = (byte) (values[5]);
    buf[off++] = (byte) (values[6]);
    buf[off] = (byte) (values[7]);
  }

  static void packBits9(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 1);

    buf[off] = (byte) (values[0] << 7);
    buf[off++] |= values[1] >>> 2;

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 3;

    buf[off] = (byte) (values[2] << 5);
    buf[off++] |= values[3] >>> 4;

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 5;

    buf[off] = (byte) (values[4] << 3);
    buf[off++] |= values[5] >>> 6;

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 7;

    buf[off] = (byte) (values[6] << 1);
    buf[off++] |= values[7] >>> 8;

    buf[off] = (byte) (values[7]);
  }

  static void packBits10(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 2);

    buf[off] = (byte) (values[0] << 6);
    buf[off++] |= values[1] >>> 4;

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 6;

    buf[off] = (byte) (values[2] << 2);
    buf[off++] |= values[3] >>> 8;

    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 2);

    buf[off] = (byte) (values[4] << 6);
    buf[off++] |= values[5] >>> 4;

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 6;

    buf[off] = (byte) (values[6] << 2);
    buf[off++] |= values[7] >>> 8;

    buf[off] = (byte) (values[7]);
  }

  static void packBits11(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 3);

    buf[off] = (byte) (values[0] << 5);
    buf[off++] |= values[1] >>> 6;

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 9;

    buf[off++] = (byte) (values[2] >>> 1);

    buf[off] = (byte) (values[2] << 7);
    buf[off++] |= values[3] >>> 4;

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 7;

    buf[off] = (byte) (values[4] << 1);
    buf[off++] |= values[5] >>> 10;

    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 5;

    buf[off] = (byte) (values[6] << 3);
    buf[off++] |= values[7] >>> 8;

    buf[off] = (byte) (values[7]);
  }

  static void packBits12(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 4);

    buf[off] = (byte) (values[0] << 4);
    buf[off++] |= values[1] >>> 8;

    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 4);

    buf[off] = (byte) (values[2] << 4);
    buf[off++] |= values[3] >>> 8;

    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 4);

    buf[off] = (byte) (values[4] << 4);
    buf[off++] |= values[5] >>> 8;

    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 4);

    buf[off] = (byte) (values[6] << 4);
    buf[off++] |= values[7] >>> 8;

    buf[off] = (byte) (values[7]);
  }

  static void packBits13(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 5);

    buf[off] = (byte) (values[0] << 3);
    buf[off++] |= values[1] >>> 10;

    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 7;

    buf[off] = (byte) (values[2] << 1);
    buf[off++] |= values[3] >>> 12;

    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] >>> 4);
    buf[off++] |= values[4] >>> 9;

    buf[off++] = (byte) (values[4] >>> 1);

    buf[off] = (byte) (values[4] << 7);
    buf[off++] |= values[5] >>> 6;

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 11;

    buf[off++] = (byte) (values[6] >>> 3);

    buf[off] = (byte) (values[6] << 5);
    buf[off++] |= values[7] >>> 8;

    buf[off] = (byte) (values[7]);
  }

  static void packBits14(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 6);

    buf[off] = (byte) (values[0] << 2);
    buf[off++] |= values[1] >>> 12;

    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 10;

    buf[off++] = (byte) (values[2] >>> 2);

    buf[off] = (byte) (values[2] << 6);
    buf[off++] |= values[3] >>> 8;

    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 6);

    buf[off] = (byte) (values[4] << 2);
    buf[off++] |= values[5] >>> 12;

    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 10;

    buf[off++] = (byte) (values[6] >>> 2);

    buf[off] = (byte) (values[6] << 6);
    buf[off++] |= values[7] >>> 8;

    buf[off] = (byte) (values[7]);
  }

  static void packBits15(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 7);

    buf[off] = (byte) (values[0] << 1);
    buf[off++] |= values[1] >>> 14;

    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 13;

    buf[off++] = (byte) (values[2] >>> 5);

    buf[off] = (byte) (values[2] << 3);
    buf[off++] |= values[3] >>> 12;

    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 11;

    buf[off++] = (byte) (values[4] >>> 3);

    buf[off] = (byte) (values[4] << 5);
    buf[off++] |= values[5] >>> 10;

    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 9;

    buf[off++] = (byte) (values[6] >>> 1);

    buf[off] = (byte) (values[6] << 7);
    buf[off++] |= values[7] >>> 8;

    buf[off] = (byte) (values[7]);
  }

  static void packBits16(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 8);
    buf[off++] = (byte) (values[0]);

    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 8);
    buf[off++] = (byte) (values[2]);

    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 8);
    buf[off++] = (byte) (values[4]);

    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 8);
    buf[off++] = (byte) (values[6]);

    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits17(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 9);

    buf[off++] = (byte) (values[0] >>> 1);

    buf[off] = (byte) (values[0] << 7);
    buf[off++] |= values[1] >>> 10;

    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 11;

    buf[off++] = (byte) (values[2] >>> 3);

    buf[off] = (byte) (values[2] << 5);
    buf[off++] |= values[3] >>> 12;

    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 13;

    buf[off++] = (byte) (values[4] >>> 5);

    buf[off] = (byte) (values[4] << 3);
    buf[off++] |= values[5] >>> 14;

    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 15;

    buf[off++] = (byte) (values[6] >>> 7);

    buf[off] = (byte) (values[6] << 1);
    buf[off++] |= values[7] >>> 16;

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits18(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 10);

    buf[off++] = (byte) (values[0] >>> 2);

    buf[off] = (byte) (values[0] << 6);
    buf[off++] |= values[1] >>> 12;

    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 14;

    buf[off++] = (byte) (values[2] >>> 6);

    buf[off] = (byte) (values[2] << 2);
    buf[off++] |= values[3] >>> 16;

    buf[off++] = (byte) (values[3] >>> 8);

    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 10);

    buf[off++] = (byte) (values[4] >>> 2);

    buf[off] = (byte) (values[4] << 6);
    buf[off++] |= values[5] >>> 12;

    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 14;

    buf[off++] = (byte) (values[6] >>> 6);

    buf[off] = (byte) (values[6] << 2);
    buf[off++] |= values[7] >>> 16;

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits19(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 11);

    buf[off++] = (byte) (values[0] >>> 3);

    buf[off] = (byte) (values[0] << 5);
    buf[off++] |= values[1] >>> 14;

    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 17;

    buf[off++] = (byte) (values[2] >>> 9);

    buf[off++] = (byte) (values[2] >>> 1);

    buf[off] = (byte) (values[2] << 7);
    buf[off++] |= values[3] >>> 12;

    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 15;

    buf[off++] |= values[4] >>> 7;

    buf[off] = (byte) (values[4] << 1);
    buf[off++] |= values[5] >>> 18;

    buf[off++] = (byte) (values[5] >>> 10);

    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 13;

    buf[off++] = (byte) (values[6] >>> 5);

    buf[off] = (byte) (values[6] << 3);
    buf[off++] |= values[7] >>> 16;

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits20(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 12);

    buf[off++] = (byte) (values[0] >>> 4);

    buf[off] = (byte) (values[0] << 4);
    buf[off++] |= values[1] >>> 16;

    buf[off++] = (byte) (values[1] >>> 8);

    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 12);

    buf[off++] = (byte) (values[2] >>> 4);

    buf[off] = (byte) (values[2] << 4);
    buf[off++] |= values[3] >>> 16;

    buf[off++] = (byte) (values[3] >>> 8);

    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 12);

    buf[off++] = (byte) (values[4] >>> 4);

    buf[off] = (byte) (values[4] << 4);
    buf[off++] |= values[5] >>> 16;

    buf[off++] = (byte) (values[5] >>> 8);

    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 12);

    buf[off++] = (byte) (values[6] >>> 4);

    buf[off] = (byte) (values[6] << 4);
    buf[off++] |= values[7] >>> 16;

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits21(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 13);

    buf[off++] = (byte) (values[0] >>> 5);

    buf[off] = (byte) (values[0] << 3);
    buf[off++] |= values[1] >>> 18;

    buf[off++] = (byte) (values[1] >>> 10);

    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 15;

    buf[off++] = (byte) (values[2] >>> 7);

    buf[off] = (byte) (values[2] << 1);
    buf[off++] |= values[3] >>> 20;

    buf[off++] = (byte) (values[3] >>> 12);

    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 17;

    buf[off++] = (byte) (values[4] >>> 9);

    buf[off++] = (byte) (values[4] >>> 1);

    buf[off] = (byte) (values[4] << 7);
    buf[off++] |= values[5] >>> 14;

    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 19;

    buf[off++] = (byte) (values[6] >>> 11);

    buf[off++] = (byte) (values[6] >>> 3);

    buf[off] = (byte) (values[6] << 5);
    buf[off++] |= values[7] >>> 16;

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits22(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 14);

    buf[off++] = (byte) (values[0] >>> 6);

    buf[off] = (byte) (values[0] << 2);
    buf[off++] |= values[1] >>> 20;

    buf[off++] = (byte) (values[1] >>> 12);

    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 18;

    buf[off++] = (byte) (values[2] >>> 10);

    buf[off++] = (byte) (values[2] >>> 2);

    buf[off] = (byte) (values[2] << 6);
    buf[off++] |= values[3] >>> 16;

    buf[off++] = (byte) (values[3] >>> 8);

    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 14);

    buf[off++] = (byte) (values[4] >>> 6);

    buf[off] = (byte) (values[4] << 2);
    buf[off++] |= values[5] >>> 20;

    buf[off++] = (byte) (values[5] >>> 12);

    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 18;

    buf[off++] = (byte) (values[6] >>> 10);

    buf[off++] = (byte) (values[6] >>> 2);

    buf[off] = (byte) (values[6] << 6);
    buf[off++] |= values[7] >>> 16;

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits23(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 15);

    buf[off++] = (byte) (values[0] >>> 7);

    buf[off] = (byte) (values[0] << 1);
    buf[off++] |= values[1] >>> 22;

    buf[off++] = (byte) (values[1] >>> 14);

    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 21;

    buf[off++] = (byte) (values[2] >>> 13);

    buf[off++] = (byte) (values[2] >>> 5);

    buf[off] = (byte) (values[2] << 3);
    buf[off++] |= values[3] >>> 20;

    buf[off++] = (byte) (values[3] >>> 12);

    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 19;

    buf[off++] = (byte) (values[4] >>> 11);

    buf[off++] = (byte) (values[4] >>> 3);

    buf[off] = (byte) (values[4] << 5);
    buf[off++] |= values[5] >>> 18;

    buf[off++] = (byte) (values[5] >>> 10);

    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 17;

    buf[off++] = (byte) (values[6] >>> 9);

    buf[off++] = (byte) (values[6] >>> 1);

    buf[off] = (byte) (values[6] << 7);
    buf[off++] |= values[7] >>> 16;

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits24(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 16);
    buf[off++] = (byte) (values[0] >>> 8);
    buf[off++] = (byte) (values[0]);

    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 16);
    buf[off++] = (byte) (values[2] >>> 8);
    buf[off++] = (byte) (values[2]);

    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 16);
    buf[off++] = (byte) (values[4] >>> 8);
    buf[off++] = (byte) (values[4]);

    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 16);
    buf[off++] = (byte) (values[6] >>> 8);
    buf[off++] = (byte) (values[6]);

    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits25(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 17);

    buf[off++] = (byte) (values[0] >>> 9);

    buf[off++] = (byte) (values[0] >>> 1);

    buf[off] = (byte) (values[0] << 7);
    buf[off++] |= values[1] >>> 18;

    buf[off++] = (byte) (values[1] >>> 10);

    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 19;

    buf[off++] = (byte) (values[2] >>> 11);

    buf[off++] = (byte) (values[2] >>> 3);

    buf[off] = (byte) (values[2] << 5);
    buf[off++] |= values[3] >>> 20;

    buf[off++] = (byte) (values[3] >>> 12);

    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 21;

    buf[off++] = (byte) (values[4] >>> 13);

    buf[off++] = (byte) (values[4] >>> 5);

    buf[off] = (byte) (values[4] << 3);
    buf[off++] |= values[5] >>> 22;

    buf[off++] = (byte) (values[5] >>> 14);

    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 23;

    buf[off++] = (byte) (values[6] >>> 15);

    buf[off++] = (byte) (values[6] >>> 7);

    buf[off] = (byte) (values[6] << 1);
    buf[off++] |= values[7] >>> 24;

    buf[off++] = (byte) (values[7] >>> 16);

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits26(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 18);

    buf[off++] = (byte) (values[0] >>> 10);

    buf[off++] = (byte) (values[0] >>> 2);

    buf[off] = (byte) (values[0] << 6);
    buf[off++] |= values[1] >>> 20;

    buf[off++] = (byte) (values[1] >>> 12);

    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 22;

    buf[off++] = (byte) (values[2] >>> 14);

    buf[off++] = (byte) (values[2] >>> 6);

    buf[off] = (byte) (values[2] << 2);
    buf[off++] |= values[3] >>> 24;

    buf[off++] = (byte) (values[3] >>> 16);

    buf[off++] = (byte) (values[3] >>> 8);

    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 18);

    buf[off++] = (byte) (values[4] >>> 10);

    buf[off++] = (byte) (values[4] >>> 2);

    buf[off] = (byte) (values[4] << 6);
    buf[off++] |= values[5] >>> 20;

    buf[off++] = (byte) (values[5] >>> 12);

    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 22;

    buf[off++] = (byte) (values[6] >>> 14);

    buf[off++] = (byte) (values[6] >>> 6);

    buf[off] = (byte) (values[6] << 2);
    buf[off++] |= values[7] >>> 24;

    buf[off++] = (byte) (values[7] >>> 16);

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits27(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 19);

    buf[off++] = (byte) (values[0] >>> 11);

    buf[off++] = (byte) (values[0] >>> 3);

    buf[off] = (byte) (values[0] << 5);
    buf[off++] |= values[1] >>> 22;

    buf[off++] = (byte) (values[1] >>> 14);

    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 25;

    buf[off++] = (byte) (values[2] >>> 17);

    buf[off++] = (byte) (values[2] >>> 9);

    buf[off++] = (byte) (values[2] >>> 1);

    buf[off] = (byte) (values[2] << 7);
    buf[off++] |= values[3] >>> 20;

    buf[off++] = (byte) (values[3] >>> 12);

    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 23;

    buf[off++] = (byte) (values[4] >>> 15);

    buf[off++] = (byte) (values[4] >>> 7);

    buf[off] = (byte) (values[4] << 1);
    buf[off++] |= values[5] >>> 26;

    buf[off++] = (byte) (values[5] >>> 18);

    buf[off++] = (byte) (values[5] >>> 10);

    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 21;

    buf[off++] = (byte) (values[6] >>> 13);

    buf[off++] = (byte) (values[6] >>> 5);

    buf[off] = (byte) (values[6] << 3);
    buf[off++] |= values[7] >>> 24;

    buf[off++] = (byte) (values[7] >>> 16);

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits28(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 20);
    buf[off++] = (byte) (values[0] >>> 12);
    buf[off++] = (byte) (values[0] >>> 4);
    buf[off] = (byte) (values[0] << 4);
    buf[off++] |= values[1] >>> 24;
    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);
    buf[off++] = (byte) (values[2] >>> 20);
    buf[off++] = (byte) (values[2] >>> 12);
    buf[off++] = (byte) (values[2] >>> 4);
    buf[off] = (byte) (values[2] << 4);
    buf[off++] |= values[3] >>> 24;
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);
    buf[off++] = (byte) (values[4] >>> 20);
    buf[off++] = (byte) (values[4] >>> 12);
    buf[off++] = (byte) (values[4] >>> 4);
    buf[off] = (byte) (values[4] << 4);
    buf[off++] |= values[5] >>> 24;
    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);
    buf[off++] = (byte) (values[6] >>> 20);
    buf[off++] = (byte) (values[6] >>> 12);
    buf[off++] = (byte) (values[6] >>> 4);
    buf[off] = (byte) (values[6] << 4);
    buf[off++] |= values[7] >>> 24;
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits29(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 21);

    buf[off++] = (byte) (values[0] >>> 13);

    buf[off++] = (byte) (values[0] >>> 5);

    buf[off] = (byte) (values[0] << 3);
    buf[off++] |= values[1] >>> 26;

    buf[off++] = (byte) (values[1] >>> 18);

    buf[off++] = (byte) (values[1] >>> 10);

    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 23;

    buf[off++] = (byte) (values[2] >>> 15);

    buf[off++] = (byte) (values[2] >>> 7);

    buf[off] = (byte) (values[2] << 1);
    buf[off++] |= values[3] >>> 28;

    buf[off++] = (byte) (values[3] >>> 20);

    buf[off++] = (byte) (values[3] >>> 12);

    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 25;

    buf[off++] = (byte) (values[4] >>> 17);

    buf[off++] = (byte) (values[4] >>> 9);

    buf[off++] = (byte) (values[4] >>> 1);

    buf[off] = (byte) (values[4] << 7);
    buf[off++] |= values[5] >>> 22;

    buf[off++] = (byte) (values[5] >>> 14);

    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 27;

    buf[off++] = (byte) (values[6] >>> 19);

    buf[off++] = (byte) (values[6] >>> 11);

    buf[off++] = (byte) (values[6] >>> 3);

    buf[off] = (byte) (values[6] << 5);
    buf[off++] |= values[7] >>> 24;

    buf[off++] = (byte) (values[7] >>> 16);

    buf[off++] = (byte) (values[7] >>> 8);

    buf[off] = (byte) (values[7]);
  }

  static void packBits30(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 22);
    buf[off++] = (byte) (values[0] >>> 14);
    buf[off++] = (byte) (values[0] >>> 6);

    buf[off] = (byte) (values[0] << 2);
    buf[off++] |= values[1] >>> 28;
    buf[off++] = (byte) (values[1] >>> 20);
    buf[off++] = (byte) (values[1] >>> 12);
    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 26;
    buf[off++] = (byte) (values[2] >>> 18);
    buf[off++] = (byte) (values[2] >>> 10);
    buf[off++] = (byte) (values[2] >>> 2);

    buf[off] = (byte) (values[2] << 6);
    buf[off++] |= values[3] >>> 24;
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 22);
    buf[off++] = (byte) (values[4] >>> 14);
    buf[off++] = (byte) (values[4] >>> 6);

    buf[off] = (byte) (values[4] << 2);
    buf[off++] |= values[5] >>> 28;
    buf[off++] = (byte) (values[5] >>> 20);
    buf[off++] = (byte) (values[5] >>> 12);
    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 26;
    buf[off++] = (byte) (values[6] >>> 18);
    buf[off++] = (byte) (values[6] >>> 10);
    buf[off++] = (byte) (values[6] >>> 2);

    buf[off] = (byte) (values[6] << 6);
    buf[off++] |= values[7] >>> 24;
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits31(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 23);
    buf[off++] = (byte) (values[0] >>> 15);
    buf[off++] = (byte) (values[0] >>> 7);

    buf[off] = (byte) (values[0] << 1);
    buf[off++] |= values[1] >>> 30;
    buf[off++] = (byte) (values[1] >>> 22);
    buf[off++] = (byte) (values[1] >>> 14);
    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 29;
    buf[off++] = (byte) (values[2] >>> 21);
    buf[off++] = (byte) (values[2] >>> 13);
    buf[off++] = (byte) (values[2] >>> 5);

    buf[off] = (byte) (values[2] << 3);
    buf[off++] |= values[3] >>> 28;
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 27;
    buf[off++] = (byte) (values[4] >>> 19);
    buf[off++] = (byte) (values[4] >>> 11);
    buf[off++] = (byte) (values[4] >>> 3);

    buf[off] = (byte) (values[4] << 5);
    buf[off++] |= values[5] >>> 26;
    buf[off++] = (byte) (values[5] >>> 18);
    buf[off++] = (byte) (values[5] >>> 10);
    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 25;
    buf[off++] = (byte) (values[6] >>> 17);
    buf[off++] = (byte) (values[6] >>> 9);
    buf[off++] = (byte) (values[6] >>> 1);

    buf[off] = (byte) (values[6] << 7);
    buf[off++] |= values[7] >>> 24;
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits32(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 24);
    buf[off++] = (byte) (values[0] >>> 16);
    buf[off++] = (byte) (values[0] >>> 8);
    buf[off++] = (byte) (values[0]);

    buf[off++] = (byte) (values[1] >>> 24);
    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 24);
    buf[off++] = (byte) (values[2] >>> 16);
    buf[off++] = (byte) (values[2] >>> 8);
    buf[off++] = (byte) (values[2]);

    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 24);
    buf[off++] = (byte) (values[4] >>> 16);
    buf[off++] = (byte) (values[4] >>> 8);
    buf[off++] = (byte) (values[4]);

    buf[off++] = (byte) (values[5] >>> 24);
    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 24);
    buf[off++] = (byte) (values[6] >>> 16);
    buf[off++] = (byte) (values[6] >>> 8);
    buf[off++] = (byte) (values[6]);

    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits33(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 25);
    buf[off++] = (byte) (values[0] >>> 17);
    buf[off++] = (byte) (values[0] >>> 9);
    buf[off++] = (byte) (values[0] >>> 1);

    buf[off] = (byte) (values[0] << 7);
    buf[off++] |= values[1] >>> 26;
    buf[off++] = (byte) (values[1] >>> 18);
    buf[off++] = (byte) (values[1] >>> 10);
    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 27;
    buf[off++] = (byte) (values[2] >>> 19);
    buf[off++] = (byte) (values[2] >>> 11);
    buf[off++] = (byte) (values[2] >>> 3);

    buf[off] = (byte) (values[2] << 5);
    buf[off++] |= values[3] >>> 28;
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 29;
    buf[off++] = (byte) (values[4] >>> 21);
    buf[off++] = (byte) (values[4] >>> 13);
    buf[off++] = (byte) (values[4] >>> 5);

    buf[off] = (byte) (values[4] << 3);
    buf[off++] |= values[5] >>> 30;
    buf[off++] = (byte) (values[5] >>> 22);
    buf[off++] = (byte) (values[5] >>> 14);
    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 31;
    buf[off++] = (byte) (values[6] >>> 23);
    buf[off++] = (byte) (values[6] >>> 15);
    buf[off++] = (byte) (values[6] >>> 7);

    buf[off] = (byte) (values[6] << 1);
    buf[off++] |= values[7] >>> 32;
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits34(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 26);
    buf[off++] = (byte) (values[0] >>> 18);
    buf[off++] = (byte) (values[0] >>> 10);
    buf[off++] = (byte) (values[0] >>> 2);

    buf[off] = (byte) (values[0] << 6);
    buf[off++] |= values[1] >>> 28;
    buf[off++] = (byte) (values[1] >>> 20);
    buf[off++] = (byte) (values[1] >>> 12);
    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 30;
    buf[off++] = (byte) (values[2] >>> 22);
    buf[off++] = (byte) (values[2] >>> 14);
    buf[off++] = (byte) (values[2] >>> 6);

    buf[off] = (byte) (values[2] << 2);
    buf[off++] |= values[3] >>> 32;
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 26);
    buf[off++] = (byte) (values[4] >>> 18);
    buf[off++] = (byte) (values[4] >>> 10);
    buf[off++] = (byte) (values[4] >>> 2);

    buf[off] = (byte) (values[4] << 6);
    buf[off++] |= values[5] >>> 28;
    buf[off++] = (byte) (values[5] >>> 20);
    buf[off++] = (byte) (values[5] >>> 12);
    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 30;
    buf[off++] = (byte) (values[6] >>> 22);
    buf[off++] = (byte) (values[6] >>> 14);
    buf[off++] = (byte) (values[6] >>> 6);

    buf[off] = (byte) (values[6] << 2);
    buf[off++] |= values[7] >>> 32;
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits35(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 27);
    buf[off++] = (byte) (values[0] >>> 19);
    buf[off++] = (byte) (values[0] >>> 11);
    buf[off++] = (byte) (values[0] >>> 3);

    buf[off] = (byte) (values[0] << 5);
    buf[off++] |= values[1] >>> 30;
    buf[off++] = (byte) (values[1] >>> 22);
    buf[off++] = (byte) (values[1] >>> 14);
    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 33;
    buf[off++] = (byte) (values[2] >>> 25);
    buf[off++] = (byte) (values[2] >>> 17);
    buf[off++] = (byte) (values[2] >>> 9);
    buf[off++] = (byte) (values[2] >>> 1);

    buf[off] = (byte) (values[2] << 7);
    buf[off++] |= values[3] >>> 28;
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 31;
    buf[off++] = (byte) (values[4] >>> 23);
    buf[off++] = (byte) (values[4] >>> 15);
    buf[off++] = (byte) (values[4] >>> 7);

    buf[off] = (byte) (values[4] << 1);
    buf[off++] |= values[5] >>> 34;
    buf[off++] = (byte) (values[5] >>> 26);
    buf[off++] = (byte) (values[5] >>> 18);
    buf[off++] = (byte) (values[5] >>> 10);
    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 29;
    buf[off++] = (byte) (values[6] >>> 21);
    buf[off++] = (byte) (values[6] >>> 13);
    buf[off++] = (byte) (values[6] >>> 5);

    buf[off] = (byte) (values[6] << 3);
    buf[off++] |= values[7] >>> 32;
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits36(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 28);
    buf[off++] = (byte) (values[0] >>> 20);
    buf[off++] = (byte) (values[0] >>> 12);
    buf[off++] = (byte) (values[0] >>> 4);

    buf[off] = (byte) (values[0] << 4);
    buf[off++] |= values[1] >>> 32;
    buf[off++] = (byte) (values[1] >>> 24);
    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 28);
    buf[off++] = (byte) (values[2] >>> 20);
    buf[off++] = (byte) (values[2] >>> 12);
    buf[off++] = (byte) (values[2] >>> 4);

    buf[off] = (byte) (values[2] << 4);
    buf[off++] |= values[3] >>> 32;
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 28);
    buf[off++] = (byte) (values[4] >>> 20);
    buf[off++] = (byte) (values[4] >>> 12);
    buf[off++] = (byte) (values[4] >>> 4);

    buf[off] = (byte) (values[4] << 4);
    buf[off++] |= values[5] >>> 32;
    buf[off++] = (byte) (values[5] >>> 24);
    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 28);
    buf[off++] = (byte) (values[6] >>> 20);
    buf[off++] = (byte) (values[6] >>> 12);
    buf[off++] = (byte) (values[6] >>> 4);

    buf[off] = (byte) (values[6] << 4);
    buf[off++] |= values[7] >>> 32;
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits37(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 29);
    buf[off++] = (byte) (values[0] >>> 21);
    buf[off++] = (byte) (values[0] >>> 13);
    buf[off++] = (byte) (values[0] >>> 5);

    buf[off] = (byte) (values[0] << 3);
    buf[off++] |= values[1] >>> 34;
    buf[off++] = (byte) (values[1] >>> 26);
    buf[off++] = (byte) (values[1] >>> 18);
    buf[off++] = (byte) (values[1] >>> 10);
    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 31;
    buf[off++] = (byte) (values[2] >>> 23);
    buf[off++] = (byte) (values[2] >>> 15);
    buf[off++] = (byte) (values[2] >>> 7);

    buf[off] = (byte) (values[2] << 1);
    buf[off++] |= values[3] >>> 36;
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 33;
    buf[off++] = (byte) (values[4] >>> 25);
    buf[off++] = (byte) (values[4] >>> 17);
    buf[off++] = (byte) (values[4] >>> 9);
    buf[off++] = (byte) (values[4] >>> 1);

    buf[off] = (byte) (values[4] << 7);
    buf[off++] |= values[5] >>> 30;
    buf[off++] = (byte) (values[5] >>> 22);
    buf[off++] = (byte) (values[5] >>> 14);
    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 35;
    buf[off++] = (byte) (values[6] >>> 27);
    buf[off++] = (byte) (values[6] >>> 19);
    buf[off++] = (byte) (values[6] >>> 11);
    buf[off++] = (byte) (values[6] >>> 3);

    buf[off] = (byte) (values[6] << 5);
    buf[off++] |= values[7] >>> 32;
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits38(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 30);
    buf[off++] = (byte) (values[0] >>> 22);
    buf[off++] = (byte) (values[0] >>> 14);
    buf[off++] = (byte) (values[0] >>> 6);

    buf[off] = (byte) (values[0] << 2);
    buf[off++] |= values[1] >>> 36;
    buf[off++] = (byte) (values[1] >>> 28);
    buf[off++] = (byte) (values[1] >>> 20);
    buf[off++] = (byte) (values[1] >>> 12);
    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 34;
    buf[off++] = (byte) (values[2] >>> 26);
    buf[off++] = (byte) (values[2] >>> 18);
    buf[off++] = (byte) (values[2] >>> 10);
    buf[off++] = (byte) (values[2] >>> 2);

    buf[off] = (byte) (values[2] << 6);
    buf[off++] |= values[3] >>> 32;
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 30);
    buf[off++] = (byte) (values[4] >>> 22);
    buf[off++] = (byte) (values[4] >>> 14);
    buf[off++] = (byte) (values[4] >>> 6);

    buf[off] = (byte) (values[4] << 2);
    buf[off++] |= values[5] >>> 36;
    buf[off++] = (byte) (values[5] >>> 28);
    buf[off++] = (byte) (values[5] >>> 20);
    buf[off++] = (byte) (values[5] >>> 12);
    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 34;
    buf[off++] = (byte) (values[6] >>> 26);
    buf[off++] = (byte) (values[6] >>> 18);
    buf[off++] = (byte) (values[6] >>> 10);
    buf[off++] = (byte) (values[6] >>> 2);

    buf[off] = (byte) (values[6] << 6);
    buf[off++] |= values[7] >>> 32;
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits39(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 31);
    buf[off++] = (byte) (values[0] >>> 23);
    buf[off++] = (byte) (values[0] >>> 15);
    buf[off++] = (byte) (values[0] >>> 7);

    buf[off] = (byte) (values[0] << 1);
    buf[off++] |= values[1] >>> 38;
    buf[off++] = (byte) (values[1] >>> 30);
    buf[off++] = (byte) (values[1] >>> 22);
    buf[off++] = (byte) (values[1] >>> 14);
    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 37;
    buf[off++] = (byte) (values[2] >>> 29);
    buf[off++] = (byte) (values[2] >>> 21);
    buf[off++] = (byte) (values[2] >>> 13);
    buf[off++] = (byte) (values[2] >>> 5);

    buf[off] = (byte) (values[2] << 3);
    buf[off++] |= values[3] >>> 36;
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 35;
    buf[off++] = (byte) (values[4] >>> 27);
    buf[off++] = (byte) (values[4] >>> 19);
    buf[off++] = (byte) (values[4] >>> 11);
    buf[off++] = (byte) (values[4] >>> 3);

    buf[off] = (byte) (values[4] << 5);
    buf[off++] |= values[5] >>> 34;
    buf[off++] = (byte) (values[5] >>> 26);
    buf[off++] = (byte) (values[5] >>> 18);
    buf[off++] = (byte) (values[5] >>> 10);
    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 33;
    buf[off++] = (byte) (values[6] >>> 25);
    buf[off++] = (byte) (values[6] >>> 17);
    buf[off++] = (byte) (values[6] >>> 9);
    buf[off++] = (byte) (values[6] >>> 1);

    buf[off] = (byte) (values[6] << 7);
    buf[off++] |= values[7] >>> 32;
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits40(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 32);
    buf[off++] = (byte) (values[0] >>> 24);
    buf[off++] = (byte) (values[0] >>> 16);
    buf[off++] = (byte) (values[0] >>> 8);
    buf[off++] = (byte) (values[0]);

    buf[off++] = (byte) (values[1] >>> 32);
    buf[off++] = (byte) (values[1] >>> 24);
    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 32);
    buf[off++] = (byte) (values[2] >>> 24);
    buf[off++] = (byte) (values[2] >>> 16);
    buf[off++] = (byte) (values[2] >>> 8);
    buf[off++] = (byte) (values[2]);

    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 32);
    buf[off++] = (byte) (values[4] >>> 24);
    buf[off++] = (byte) (values[4] >>> 16);
    buf[off++] = (byte) (values[4] >>> 8);
    buf[off++] = (byte) (values[4]);

    buf[off++] = (byte) (values[5] >>> 32);
    buf[off++] = (byte) (values[5] >>> 24);
    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 32);
    buf[off++] = (byte) (values[6] >>> 24);
    buf[off++] = (byte) (values[6] >>> 16);
    buf[off++] = (byte) (values[6] >>> 8);
    buf[off++] = (byte) (values[6]);

    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits41(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 33);
    buf[off++] = (byte) (values[0] >>> 25);
    buf[off++] = (byte) (values[0] >>> 17);
    buf[off++] = (byte) (values[0] >>> 9);
    buf[off++] = (byte) (values[0] >>> 1);

    buf[off] = (byte) (values[0] << 7);
    buf[off++] |= values[1] >>> 34;
    buf[off++] = (byte) (values[1] >>> 26);
    buf[off++] = (byte) (values[1] >>> 18);
    buf[off++] = (byte) (values[1] >>> 10);
    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 35;
    buf[off++] = (byte) (values[2] >>> 27);
    buf[off++] = (byte) (values[2] >>> 19);
    buf[off++] = (byte) (values[2] >>> 11);
    buf[off++] = (byte) (values[2] >>> 3);

    buf[off] = (byte) (values[2] << 5);
    buf[off++] |= values[3] >>> 36;
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 37;
    buf[off++] = (byte) (values[4] >>> 29);
    buf[off++] = (byte) (values[4] >>> 21);
    buf[off++] = (byte) (values[4] >>> 13);
    buf[off++] = (byte) (values[4] >>> 5);

    buf[off] = (byte) (values[4] << 3);
    buf[off++] |= values[5] >>> 38;
    buf[off++] = (byte) (values[5] >>> 30);
    buf[off++] = (byte) (values[5] >>> 22);
    buf[off++] = (byte) (values[5] >>> 14);
    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 39;
    buf[off++] = (byte) (values[6] >>> 31);
    buf[off++] = (byte) (values[6] >>> 23);
    buf[off++] = (byte) (values[6] >>> 15);
    buf[off++] = (byte) (values[6] >>> 7);

    buf[off] = (byte) (values[6] << 1);
    buf[off++] |= values[7] >>> 40;
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits42(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 34);
    buf[off++] = (byte) (values[0] >>> 26);
    buf[off++] = (byte) (values[0] >>> 18);
    buf[off++] = (byte) (values[0] >>> 10);
    buf[off++] = (byte) (values[0] >>> 2);

    buf[off] = (byte) (values[0] << 6);
    buf[off++] |= values[1] >>> 36;
    buf[off++] = (byte) (values[1] >>> 28);
    buf[off++] = (byte) (values[1] >>> 20);
    buf[off++] = (byte) (values[1] >>> 12);
    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 38;
    buf[off++] = (byte) (values[2] >>> 30);
    buf[off++] = (byte) (values[2] >>> 22);
    buf[off++] = (byte) (values[2] >>> 14);
    buf[off++] = (byte) (values[2] >>> 6);

    buf[off] = (byte) (values[2] << 2);
    buf[off++] |= values[3] >>> 40;
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 34);
    buf[off++] = (byte) (values[4] >>> 26);
    buf[off++] = (byte) (values[4] >>> 18);
    buf[off++] = (byte) (values[4] >>> 10);
    buf[off++] = (byte) (values[4] >>> 2);

    buf[off] = (byte) (values[4] << 6);
    buf[off++] |= values[5] >>> 36;
    buf[off++] = (byte) (values[5] >>> 28);
    buf[off++] = (byte) (values[5] >>> 20);
    buf[off++] = (byte) (values[5] >>> 12);
    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 38;
    buf[off++] = (byte) (values[6] >>> 30);
    buf[off++] = (byte) (values[6] >>> 22);
    buf[off++] = (byte) (values[6] >>> 14);
    buf[off++] = (byte) (values[6] >>> 6);

    buf[off] = (byte) (values[6] << 2);
    buf[off++] |= values[7] >>> 40;
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits43(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 35);
    buf[off++] = (byte) (values[0] >>> 27);
    buf[off++] = (byte) (values[0] >>> 19);
    buf[off++] = (byte) (values[0] >>> 11);
    buf[off++] = (byte) (values[0] >>> 3);

    buf[off] = (byte) (values[0] << 5);
    buf[off++] |= values[1] >>> 38;
    buf[off++] = (byte) (values[1] >>> 30);
    buf[off++] = (byte) (values[1] >>> 22);
    buf[off++] = (byte) (values[1] >>> 14);
    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 41;
    buf[off++] = (byte) (values[2] >>> 33);
    buf[off++] = (byte) (values[2] >>> 25);
    buf[off++] = (byte) (values[2] >>> 17);
    buf[off++] = (byte) (values[2] >>> 9);
    buf[off++] = (byte) (values[2] >>> 1);

    buf[off] = (byte) (values[2] << 7);
    buf[off++] |= values[3] >>> 36;
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 39;
    buf[off++] = (byte) (values[4] >>> 31);
    buf[off++] = (byte) (values[4] >>> 23);
    buf[off++] = (byte) (values[4] >>> 15);
    buf[off++] = (byte) (values[4] >>> 7);

    buf[off] = (byte) (values[4] << 1);
    buf[off++] |= values[5] >>> 42;
    buf[off++] = (byte) (values[5] >>> 34);
    buf[off++] = (byte) (values[5] >>> 26);
    buf[off++] = (byte) (values[5] >>> 18);
    buf[off++] = (byte) (values[5] >>> 10);
    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 37;
    buf[off++] = (byte) (values[6] >>> 29);
    buf[off++] = (byte) (values[6] >>> 21);
    buf[off++] = (byte) (values[6] >>> 13);
    buf[off++] = (byte) (values[6] >>> 5);

    buf[off] = (byte) (values[6] << 3);
    buf[off++] |= values[7] >>> 40;
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits44(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 36);
    buf[off++] = (byte) (values[0] >>> 28);
    buf[off++] = (byte) (values[0] >>> 20);
    buf[off++] = (byte) (values[0] >>> 12);
    buf[off++] = (byte) (values[0] >>> 4);

    buf[off] = (byte) (values[0] << 4);
    buf[off++] |= values[1] >>> 40;
    buf[off++] = (byte) (values[1] >>> 32);
    buf[off++] = (byte) (values[1] >>> 24);
    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 36);
    buf[off++] = (byte) (values[2] >>> 28);
    buf[off++] = (byte) (values[2] >>> 20);
    buf[off++] = (byte) (values[2] >>> 12);
    buf[off++] = (byte) (values[2] >>> 4);

    buf[off] = (byte) (values[2] << 4);
    buf[off++] |= values[3] >>> 40;
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 36);
    buf[off++] = (byte) (values[4] >>> 28);
    buf[off++] = (byte) (values[4] >>> 20);
    buf[off++] = (byte) (values[4] >>> 12);
    buf[off++] = (byte) (values[4] >>> 4);

    buf[off] = (byte) (values[4] << 4);
    buf[off++] |= values[5] >>> 40;
    buf[off++] = (byte) (values[5] >>> 32);
    buf[off++] = (byte) (values[5] >>> 24);
    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 36);
    buf[off++] = (byte) (values[6] >>> 28);
    buf[off++] = (byte) (values[6] >>> 20);
    buf[off++] = (byte) (values[6] >>> 12);
    buf[off++] = (byte) (values[6] >>> 4);

    buf[off] = (byte) (values[6] << 4);
    buf[off++] |= values[7] >>> 40;
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits45(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 37);
    buf[off++] = (byte) (values[0] >>> 29);
    buf[off++] = (byte) (values[0] >>> 21);
    buf[off++] = (byte) (values[0] >>> 13);
    buf[off++] = (byte) (values[0] >>> 5);

    buf[off] = (byte) (values[0] << 3);
    buf[off++] |= values[1] >>> 42;
    buf[off++] = (byte) (values[1] >>> 34);
    buf[off++] = (byte) (values[1] >>> 26);
    buf[off++] = (byte) (values[1] >>> 18);
    buf[off++] = (byte) (values[1] >>> 10);
    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 39;
    buf[off++] = (byte) (values[2] >>> 31);
    buf[off++] = (byte) (values[2] >>> 23);
    buf[off++] = (byte) (values[2] >>> 15);
    buf[off++] = (byte) (values[2] >>> 7);

    buf[off] = (byte) (values[2] << 1);
    buf[off++] |= values[3] >>> 44;
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 41;
    buf[off++] = (byte) (values[4] >>> 33);
    buf[off++] = (byte) (values[4] >>> 25);
    buf[off++] = (byte) (values[4] >>> 17);
    buf[off++] = (byte) (values[4] >>> 9);
    buf[off++] = (byte) (values[4] >>> 1);

    buf[off] = (byte) (values[4] << 7);
    buf[off++] |= values[5] >>> 38;
    buf[off++] = (byte) (values[5] >>> 30);
    buf[off++] = (byte) (values[5] >>> 22);
    buf[off++] = (byte) (values[5] >>> 14);
    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 43;
    buf[off++] = (byte) (values[6] >>> 35);
    buf[off++] = (byte) (values[6] >>> 27);
    buf[off++] = (byte) (values[6] >>> 19);
    buf[off++] = (byte) (values[6] >>> 11);
    buf[off++] = (byte) (values[6] >>> 3);

    buf[off] = (byte) (values[6] << 5);
    buf[off++] |= values[7] >>> 40;
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits46(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 38);
    buf[off++] = (byte) (values[0] >>> 30);
    buf[off++] = (byte) (values[0] >>> 22);
    buf[off++] = (byte) (values[0] >>> 14);
    buf[off++] = (byte) (values[0] >>> 6);

    buf[off] = (byte) (values[0] << 2);
    buf[off++] |= values[1] >>> 44;
    buf[off++] = (byte) (values[1] >>> 36);
    buf[off++] = (byte) (values[1] >>> 28);
    buf[off++] = (byte) (values[1] >>> 20);
    buf[off++] = (byte) (values[1] >>> 12);
    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 42;
    buf[off++] = (byte) (values[2] >>> 34);
    buf[off++] = (byte) (values[2] >>> 26);
    buf[off++] = (byte) (values[2] >>> 18);
    buf[off++] = (byte) (values[2] >>> 10);
    buf[off++] = (byte) (values[2] >>> 2);

    buf[off] = (byte) (values[2] << 6);
    buf[off++] |= values[3] >>> 40;
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 38);
    buf[off++] = (byte) (values[4] >>> 30);
    buf[off++] = (byte) (values[4] >>> 22);
    buf[off++] = (byte) (values[4] >>> 14);
    buf[off++] = (byte) (values[4] >>> 6);

    buf[off] = (byte) (values[4] << 2);
    buf[off++] |= values[5] >>> 44;
    buf[off++] = (byte) (values[5] >>> 36);
    buf[off++] = (byte) (values[5] >>> 28);
    buf[off++] = (byte) (values[5] >>> 20);
    buf[off++] = (byte) (values[5] >>> 12);
    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 42;
    buf[off++] = (byte) (values[6] >>> 34);
    buf[off++] = (byte) (values[6] >>> 26);
    buf[off++] = (byte) (values[6] >>> 18);
    buf[off++] = (byte) (values[6] >>> 10);
    buf[off++] = (byte) (values[6] >>> 2);

    buf[off] = (byte) (values[6] << 6);
    buf[off++] |= values[7] >>> 40;
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits47(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 39);
    buf[off++] = (byte) (values[0] >>> 31);
    buf[off++] = (byte) (values[0] >>> 23);
    buf[off++] = (byte) (values[0] >>> 15);
    buf[off++] = (byte) (values[0] >>> 7);

    buf[off] = (byte) (values[0] << 1);
    buf[off++] |= values[1] >>> 46;
    buf[off++] = (byte) (values[1] >>> 38);
    buf[off++] = (byte) (values[1] >>> 30);
    buf[off++] = (byte) (values[1] >>> 22);
    buf[off++] = (byte) (values[1] >>> 14);
    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 45;
    buf[off++] = (byte) (values[2] >>> 37);
    buf[off++] = (byte) (values[2] >>> 29);
    buf[off++] = (byte) (values[2] >>> 21);
    buf[off++] = (byte) (values[2] >>> 13);
    buf[off++] = (byte) (values[2] >>> 5);

    buf[off] = (byte) (values[2] << 3);
    buf[off++] |= values[3] >>> 44;
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 43;
    buf[off++] = (byte) (values[4] >>> 35);
    buf[off++] = (byte) (values[4] >>> 27);
    buf[off++] = (byte) (values[4] >>> 19);
    buf[off++] = (byte) (values[4] >>> 11);
    buf[off++] = (byte) (values[4] >>> 3);

    buf[off] = (byte) (values[4] << 5);
    buf[off++] |= values[5] >>> 42;
    buf[off++] = (byte) (values[5] >>> 34);
    buf[off++] = (byte) (values[5] >>> 26);
    buf[off++] = (byte) (values[5] >>> 18);
    buf[off++] = (byte) (values[5] >>> 10);
    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 41;
    buf[off++] = (byte) (values[6] >>> 33);
    buf[off++] = (byte) (values[6] >>> 25);
    buf[off++] = (byte) (values[6] >>> 17);
    buf[off++] = (byte) (values[6] >>> 9);
    buf[off++] = (byte) (values[6] >>> 1);

    buf[off] = (byte) (values[6] << 7);
    buf[off++] |= values[7] >>> 40;
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits48(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 40);
    buf[off++] = (byte) (values[0] >>> 32);
    buf[off++] = (byte) (values[0] >>> 24);
    buf[off++] = (byte) (values[0] >>> 16);
    buf[off++] = (byte) (values[0] >>> 8);
    buf[off++] = (byte) (values[0]);

    buf[off++] = (byte) (values[1] >>> 40);
    buf[off++] = (byte) (values[1] >>> 32);
    buf[off++] = (byte) (values[1] >>> 24);
    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 40);
    buf[off++] = (byte) (values[2] >>> 32);
    buf[off++] = (byte) (values[2] >>> 24);
    buf[off++] = (byte) (values[2] >>> 16);
    buf[off++] = (byte) (values[2] >>> 8);
    buf[off++] = (byte) (values[2]);

    buf[off++] = (byte) (values[3] >>> 40);
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 40);
    buf[off++] = (byte) (values[4] >>> 32);
    buf[off++] = (byte) (values[4] >>> 24);
    buf[off++] = (byte) (values[4] >>> 16);
    buf[off++] = (byte) (values[4] >>> 8);
    buf[off++] = (byte) (values[4]);

    buf[off++] = (byte) (values[5] >>> 40);
    buf[off++] = (byte) (values[5] >>> 32);
    buf[off++] = (byte) (values[5] >>> 24);
    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 40);
    buf[off++] = (byte) (values[6] >>> 32);
    buf[off++] = (byte) (values[6] >>> 24);
    buf[off++] = (byte) (values[6] >>> 16);
    buf[off++] = (byte) (values[6] >>> 8);
    buf[off++] = (byte) (values[6]);

    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits49(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 41);
    buf[off++] = (byte) (values[0] >>> 33);
    buf[off++] = (byte) (values[0] >>> 25);
    buf[off++] = (byte) (values[0] >>> 17);
    buf[off++] = (byte) (values[0] >>> 9);
    buf[off++] = (byte) (values[0] >>> 1);

    buf[off] = (byte) (values[0] << 7);
    buf[off++] |= values[1] >>> 42;
    buf[off++] = (byte) (values[1] >>> 34);
    buf[off++] = (byte) (values[1] >>> 26);
    buf[off++] = (byte) (values[1] >>> 18);
    buf[off++] = (byte) (values[1] >>> 10);
    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 43;
    buf[off++] = (byte) (values[2] >>> 35);
    buf[off++] = (byte) (values[2] >>> 27);
    buf[off++] = (byte) (values[2] >>> 19);
    buf[off++] = (byte) (values[2] >>> 11);
    buf[off++] = (byte) (values[2] >>> 3);

    buf[off] = (byte) (values[2] << 5);
    buf[off++] |= values[3] >>> 44;
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 45;
    buf[off++] = (byte) (values[4] >>> 37);
    buf[off++] = (byte) (values[4] >>> 29);
    buf[off++] = (byte) (values[4] >>> 21);
    buf[off++] = (byte) (values[4] >>> 13);
    buf[off++] = (byte) (values[4] >>> 5);

    buf[off] = (byte) (values[4] << 3);
    buf[off++] |= values[5] >>> 46;
    buf[off++] = (byte) (values[5] >>> 38);
    buf[off++] = (byte) (values[5] >>> 30);
    buf[off++] = (byte) (values[5] >>> 22);
    buf[off++] = (byte) (values[5] >>> 14);
    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 47;
    buf[off++] = (byte) (values[6] >>> 39);
    buf[off++] = (byte) (values[6] >>> 31);
    buf[off++] = (byte) (values[6] >>> 23);
    buf[off++] = (byte) (values[6] >>> 15);
    buf[off++] = (byte) (values[6] >>> 7);

    buf[off] = (byte) (values[6] << 1);
    buf[off++] |= values[7] >>> 48;
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits50(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 42);
    buf[off++] = (byte) (values[0] >>> 34);
    buf[off++] = (byte) (values[0] >>> 26);
    buf[off++] = (byte) (values[0] >>> 18);
    buf[off++] = (byte) (values[0] >>> 10);
    buf[off++] = (byte) (values[0] >>> 2);

    buf[off] = (byte) (values[0] << 6);
    buf[off++] |= values[1] >>> 44;
    buf[off++] = (byte) (values[1] >>> 36);
    buf[off++] = (byte) (values[1] >>> 28);
    buf[off++] = (byte) (values[1] >>> 20);
    buf[off++] = (byte) (values[1] >>> 12);
    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 46;
    buf[off++] = (byte) (values[2] >>> 38);
    buf[off++] = (byte) (values[2] >>> 30);
    buf[off++] = (byte) (values[2] >>> 22);
    buf[off++] = (byte) (values[2] >>> 14);
    buf[off++] = (byte) (values[2] >>> 6);

    buf[off] = (byte) (values[2] << 2);
    buf[off++] |= values[3] >>> 48;
    buf[off++] = (byte) (values[3] >>> 40);
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 42);
    buf[off++] = (byte) (values[4] >>> 34);
    buf[off++] = (byte) (values[4] >>> 26);
    buf[off++] = (byte) (values[4] >>> 18);
    buf[off++] = (byte) (values[4] >>> 10);
    buf[off++] = (byte) (values[4] >>> 2);

    buf[off] = (byte) (values[4] << 6);
    buf[off++] |= values[5] >>> 44;
    buf[off++] = (byte) (values[5] >>> 36);
    buf[off++] = (byte) (values[5] >>> 28);
    buf[off++] = (byte) (values[5] >>> 20);
    buf[off++] = (byte) (values[5] >>> 12);
    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 46;
    buf[off++] = (byte) (values[6] >>> 38);
    buf[off++] = (byte) (values[6] >>> 30);
    buf[off++] = (byte) (values[6] >>> 22);
    buf[off++] = (byte) (values[6] >>> 14);
    buf[off++] = (byte) (values[6] >>> 6);

    buf[off] = (byte) (values[6] << 2);
    buf[off++] |= values[7] >>> 48;
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits51(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 43);
    buf[off++] = (byte) (values[0] >>> 35);
    buf[off++] = (byte) (values[0] >>> 27);
    buf[off++] = (byte) (values[0] >>> 19);
    buf[off++] = (byte) (values[0] >>> 11);
    buf[off++] = (byte) (values[0] >>> 3);

    buf[off] = (byte) (values[0] << 5);
    buf[off++] |= values[1] >>> 46;
    buf[off++] = (byte) (values[1] >>> 38);
    buf[off++] = (byte) (values[1] >>> 30);
    buf[off++] = (byte) (values[1] >>> 22);
    buf[off++] = (byte) (values[1] >>> 14);
    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 49;
    buf[off++] = (byte) (values[2] >>> 41);
    buf[off++] = (byte) (values[2] >>> 33);
    buf[off++] = (byte) (values[2] >>> 25);
    buf[off++] = (byte) (values[2] >>> 17);
    buf[off++] = (byte) (values[2] >>> 9);
    buf[off++] = (byte) (values[2] >>> 1);

    buf[off] = (byte) (values[2] << 7);
    buf[off++] |= values[3] >>> 44;
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 47;
    buf[off++] = (byte) (values[4] >>> 39);
    buf[off++] = (byte) (values[4] >>> 31);
    buf[off++] = (byte) (values[4] >>> 23);
    buf[off++] = (byte) (values[4] >>> 15);
    buf[off++] = (byte) (values[4] >>> 7);

    buf[off] = (byte) (values[4] << 1);
    buf[off++] |= values[5] >>> 50;
    buf[off++] = (byte) (values[5] >>> 42);
    buf[off++] = (byte) (values[5] >>> 34);
    buf[off++] = (byte) (values[5] >>> 26);
    buf[off++] = (byte) (values[5] >>> 18);
    buf[off++] = (byte) (values[5] >>> 10);
    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 45;
    buf[off++] = (byte) (values[6] >>> 37);
    buf[off++] = (byte) (values[6] >>> 29);
    buf[off++] = (byte) (values[6] >>> 21);
    buf[off++] = (byte) (values[6] >>> 13);
    buf[off++] = (byte) (values[6] >>> 5);

    buf[off] = (byte) (values[6] << 3);
    buf[off++] |= values[7] >>> 48;
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits52(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 44);
    buf[off++] = (byte) (values[0] >>> 36);
    buf[off++] = (byte) (values[0] >>> 28);
    buf[off++] = (byte) (values[0] >>> 20);
    buf[off++] = (byte) (values[0] >>> 12);
    buf[off++] = (byte) (values[0] >>> 4);

    buf[off] = (byte) (values[0] << 4);
    buf[off++] |= values[1] >>> 48;
    buf[off++] = (byte) (values[1] >>> 40);
    buf[off++] = (byte) (values[1] >>> 32);
    buf[off++] = (byte) (values[1] >>> 24);
    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 44);
    buf[off++] = (byte) (values[2] >>> 36);
    buf[off++] = (byte) (values[2] >>> 28);
    buf[off++] = (byte) (values[2] >>> 20);
    buf[off++] = (byte) (values[2] >>> 12);
    buf[off++] = (byte) (values[2] >>> 4);

    buf[off] = (byte) (values[2] << 4);
    buf[off++] |= values[3] >>> 48;
    buf[off++] = (byte) (values[3] >>> 40);
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 44);
    buf[off++] = (byte) (values[4] >>> 36);
    buf[off++] = (byte) (values[4] >>> 28);
    buf[off++] = (byte) (values[4] >>> 20);
    buf[off++] = (byte) (values[4] >>> 12);
    buf[off++] = (byte) (values[4] >>> 4);

    buf[off] = (byte) (values[4] << 4);
    buf[off++] |= values[5] >>> 48;
    buf[off++] = (byte) (values[5] >>> 40);
    buf[off++] = (byte) (values[5] >>> 32);
    buf[off++] = (byte) (values[5] >>> 24);
    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 44);
    buf[off++] = (byte) (values[6] >>> 36);
    buf[off++] = (byte) (values[6] >>> 28);
    buf[off++] = (byte) (values[6] >>> 20);
    buf[off++] = (byte) (values[6] >>> 12);
    buf[off++] = (byte) (values[6] >>> 4);

    buf[off] = (byte) (values[6] << 4);
    buf[off++] |= values[7] >>> 48;
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits53(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 45);
    buf[off++] = (byte) (values[0] >>> 37);
    buf[off++] = (byte) (values[0] >>> 29);
    buf[off++] = (byte) (values[0] >>> 21);
    buf[off++] = (byte) (values[0] >>> 13);
    buf[off++] = (byte) (values[0] >>> 5);

    buf[off] = (byte) (values[0] << 3);
    buf[off++] |= values[1] >>> 50;
    buf[off++] = (byte) (values[1] >>> 42);
    buf[off++] = (byte) (values[1] >>> 34);
    buf[off++] = (byte) (values[1] >>> 26);
    buf[off++] = (byte) (values[1] >>> 18);
    buf[off++] = (byte) (values[1] >>> 10);
    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 47;
    buf[off++] = (byte) (values[2] >>> 39);
    buf[off++] = (byte) (values[2] >>> 31);
    buf[off++] = (byte) (values[2] >>> 23);
    buf[off++] = (byte) (values[2] >>> 15);
    buf[off++] = (byte) (values[2] >>> 7);

    buf[off] = (byte) (values[2] << 1);
    buf[off++] |= values[3] >>> 52;
    buf[off++] = (byte) (values[3] >>> 44);
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 49;
    buf[off++] = (byte) (values[4] >>> 41);
    buf[off++] = (byte) (values[4] >>> 33);
    buf[off++] = (byte) (values[4] >>> 25);
    buf[off++] = (byte) (values[4] >>> 17);
    buf[off++] = (byte) (values[4] >>> 9);
    buf[off++] = (byte) (values[4] >>> 1);

    buf[off] = (byte) (values[4] << 7);
    buf[off++] |= values[5] >>> 46;
    buf[off++] = (byte) (values[5] >>> 38);
    buf[off++] = (byte) (values[5] >>> 30);
    buf[off++] = (byte) (values[5] >>> 22);
    buf[off++] = (byte) (values[5] >>> 14);
    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 51;
    buf[off++] = (byte) (values[6] >>> 43);
    buf[off++] = (byte) (values[6] >>> 35);
    buf[off++] = (byte) (values[6] >>> 27);
    buf[off++] = (byte) (values[6] >>> 19);
    buf[off++] = (byte) (values[6] >>> 11);
    buf[off++] = (byte) (values[6] >>> 3);

    buf[off] = (byte) (values[6] << 5);
    buf[off++] |= values[7] >>> 48;
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits54(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 46);
    buf[off++] = (byte) (values[0] >>> 38);
    buf[off++] = (byte) (values[0] >>> 30);
    buf[off++] = (byte) (values[0] >>> 22);
    buf[off++] = (byte) (values[0] >>> 14);
    buf[off++] = (byte) (values[0] >>> 6);

    buf[off] = (byte) (values[0] << 2);
    buf[off++] |= values[1] >>> 52;
    buf[off++] = (byte) (values[1] >>> 44);
    buf[off++] = (byte) (values[1] >>> 36);
    buf[off++] = (byte) (values[1] >>> 28);
    buf[off++] = (byte) (values[1] >>> 20);
    buf[off++] = (byte) (values[1] >>> 12);
    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 50;
    buf[off++] = (byte) (values[2] >>> 42);
    buf[off++] = (byte) (values[2] >>> 34);
    buf[off++] = (byte) (values[2] >>> 26);
    buf[off++] = (byte) (values[2] >>> 18);
    buf[off++] = (byte) (values[2] >>> 10);
    buf[off++] = (byte) (values[2] >>> 2);

    buf[off] = (byte) (values[2] << 6);
    buf[off++] |= values[3] >>> 48;
    buf[off++] = (byte) (values[3] >>> 40);
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 46);
    buf[off++] = (byte) (values[4] >>> 38);
    buf[off++] = (byte) (values[4] >>> 30);
    buf[off++] = (byte) (values[4] >>> 22);
    buf[off++] = (byte) (values[4] >>> 14);
    buf[off++] = (byte) (values[4] >>> 6);

    buf[off] = (byte) (values[4] << 2);
    buf[off++] |= values[5] >>> 52;
    buf[off++] = (byte) (values[5] >>> 44);
    buf[off++] = (byte) (values[5] >>> 36);
    buf[off++] = (byte) (values[5] >>> 28);
    buf[off++] = (byte) (values[5] >>> 20);
    buf[off++] = (byte) (values[5] >>> 12);
    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 50;
    buf[off++] = (byte) (values[6] >>> 42);
    buf[off++] = (byte) (values[6] >>> 34);
    buf[off++] = (byte) (values[6] >>> 26);
    buf[off++] = (byte) (values[6] >>> 18);
    buf[off++] = (byte) (values[6] >>> 10);
    buf[off++] = (byte) (values[6] >>> 2);

    buf[off] = (byte) (values[6] << 6);
    buf[off++] |= values[7] >>> 48;
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits55(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 47);
    buf[off++] = (byte) (values[0] >>> 39);
    buf[off++] = (byte) (values[0] >>> 31);
    buf[off++] = (byte) (values[0] >>> 23);
    buf[off++] = (byte) (values[0] >>> 15);
    buf[off++] = (byte) (values[0] >>> 7);

    buf[off] = (byte) (values[0] << 1);
    buf[off++] |= values[1] >>> 54;
    buf[off++] = (byte) (values[1] >>> 46);
    buf[off++] = (byte) (values[1] >>> 38);
    buf[off++] = (byte) (values[1] >>> 30);
    buf[off++] = (byte) (values[1] >>> 22);
    buf[off++] = (byte) (values[1] >>> 14);
    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 53;
    buf[off++] = (byte) (values[2] >>> 45);
    buf[off++] = (byte) (values[2] >>> 37);
    buf[off++] = (byte) (values[2] >>> 29);
    buf[off++] = (byte) (values[2] >>> 21);
    buf[off++] = (byte) (values[2] >>> 13);
    buf[off++] = (byte) (values[2] >>> 5);

    buf[off] = (byte) (values[2] << 3);
    buf[off++] |= values[3] >>> 52;
    buf[off++] = (byte) (values[3] >>> 44);
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 51;
    buf[off++] = (byte) (values[4] >>> 43);
    buf[off++] = (byte) (values[4] >>> 35);
    buf[off++] = (byte) (values[4] >>> 27);
    buf[off++] = (byte) (values[4] >>> 19);
    buf[off++] = (byte) (values[4] >>> 11);
    buf[off++] = (byte) (values[4] >>> 3);

    buf[off] = (byte) (values[4] << 5);
    buf[off++] |= values[5] >>> 50;
    buf[off++] = (byte) (values[5] >>> 42);
    buf[off++] = (byte) (values[5] >>> 34);
    buf[off++] = (byte) (values[5] >>> 26);
    buf[off++] = (byte) (values[5] >>> 18);
    buf[off++] = (byte) (values[5] >>> 10);
    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 49;
    buf[off++] = (byte) (values[6] >>> 41);
    buf[off++] = (byte) (values[6] >>> 33);
    buf[off++] = (byte) (values[6] >>> 25);
    buf[off++] = (byte) (values[6] >>> 17);
    buf[off++] = (byte) (values[6] >>> 9);
    buf[off++] = (byte) (values[6] >>> 1);

    buf[off] = (byte) (values[6] << 7);
    buf[off++] |= values[7] >>> 48;
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits56(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 48);
    buf[off++] = (byte) (values[0] >>> 40);
    buf[off++] = (byte) (values[0] >>> 32);
    buf[off++] = (byte) (values[0] >>> 24);
    buf[off++] = (byte) (values[0] >>> 16);
    buf[off++] = (byte) (values[0] >>> 8);
    buf[off++] = (byte) (values[0]);

    buf[off++] = (byte) (values[1] >>> 48);
    buf[off++] = (byte) (values[1] >>> 40);
    buf[off++] = (byte) (values[1] >>> 32);
    buf[off++] = (byte) (values[1] >>> 24);
    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 48);
    buf[off++] = (byte) (values[2] >>> 40);
    buf[off++] = (byte) (values[2] >>> 32);
    buf[off++] = (byte) (values[2] >>> 24);
    buf[off++] = (byte) (values[2] >>> 16);
    buf[off++] = (byte) (values[2] >>> 8);
    buf[off++] = (byte) (values[2]);

    buf[off++] = (byte) (values[3] >>> 48);
    buf[off++] = (byte) (values[3] >>> 40);
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 48);
    buf[off++] = (byte) (values[4] >>> 40);
    buf[off++] = (byte) (values[4] >>> 32);
    buf[off++] = (byte) (values[4] >>> 24);
    buf[off++] = (byte) (values[4] >>> 16);
    buf[off++] = (byte) (values[4] >>> 8);
    buf[off++] = (byte) (values[4]);

    buf[off++] = (byte) (values[5] >>> 48);
    buf[off++] = (byte) (values[5] >>> 40);
    buf[off++] = (byte) (values[5] >>> 32);
    buf[off++] = (byte) (values[5] >>> 24);
    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 48);
    buf[off++] = (byte) (values[6] >>> 40);
    buf[off++] = (byte) (values[6] >>> 32);
    buf[off++] = (byte) (values[6] >>> 24);
    buf[off++] = (byte) (values[6] >>> 16);
    buf[off++] = (byte) (values[6] >>> 8);
    buf[off++] = (byte) (values[6]);

    buf[off++] = (byte) (values[7] >>> 48);
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits57(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 49);
    buf[off++] = (byte) (values[0] >>> 41);
    buf[off++] = (byte) (values[0] >>> 33);
    buf[off++] = (byte) (values[0] >>> 25);
    buf[off++] = (byte) (values[0] >>> 17);
    buf[off++] = (byte) (values[0] >>> 9);
    buf[off++] = (byte) (values[0] >>> 1);

    buf[off] = (byte) (values[0] << 7);
    buf[off++] |= values[1] >>> 50;
    buf[off++] = (byte) (values[1] >>> 42);
    buf[off++] = (byte) (values[1] >>> 34);
    buf[off++] = (byte) (values[1] >>> 26);
    buf[off++] = (byte) (values[1] >>> 18);
    buf[off++] = (byte) (values[1] >>> 10);
    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 51;
    buf[off++] = (byte) (values[2] >>> 43);
    buf[off++] = (byte) (values[2] >>> 35);
    buf[off++] = (byte) (values[2] >>> 27);
    buf[off++] = (byte) (values[2] >>> 19);
    buf[off++] = (byte) (values[2] >>> 11);
    buf[off++] = (byte) (values[2] >>> 3);

    buf[off] = (byte) (values[2] << 5);
    buf[off++] |= values[3] >>> 52;
    buf[off++] = (byte) (values[3] >>> 44);
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 53;
    buf[off++] = (byte) (values[4] >>> 45);
    buf[off++] = (byte) (values[4] >>> 37);
    buf[off++] = (byte) (values[4] >>> 29);
    buf[off++] = (byte) (values[4] >>> 21);
    buf[off++] = (byte) (values[4] >>> 13);
    buf[off++] = (byte) (values[4] >>> 5);

    buf[off] = (byte) (values[4] << 3);
    buf[off++] |= values[5] >>> 54;
    buf[off++] = (byte) (values[5] >>> 46);
    buf[off++] = (byte) (values[5] >>> 38);
    buf[off++] = (byte) (values[5] >>> 30);
    buf[off++] = (byte) (values[5] >>> 22);
    buf[off++] = (byte) (values[5] >>> 14);
    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 55;
    buf[off++] = (byte) (values[6] >>> 47);
    buf[off++] = (byte) (values[6] >>> 39);
    buf[off++] = (byte) (values[6] >>> 31);
    buf[off++] = (byte) (values[6] >>> 23);
    buf[off++] = (byte) (values[6] >>> 15);
    buf[off++] = (byte) (values[6] >>> 7);

    buf[off] = (byte) (values[6] << 1);
    buf[off++] |= values[7] >>> 56;
    buf[off++] = (byte) (values[7] >>> 48);
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits58(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 50);
    buf[off++] = (byte) (values[0] >>> 42);
    buf[off++] = (byte) (values[0] >>> 34);
    buf[off++] = (byte) (values[0] >>> 26);
    buf[off++] = (byte) (values[0] >>> 18);
    buf[off++] = (byte) (values[0] >>> 10);
    buf[off++] = (byte) (values[0] >>> 2);

    buf[off] = (byte) (values[0] << 6);
    buf[off++] |= values[1] >>> 52;
    buf[off++] = (byte) (values[1] >>> 44);
    buf[off++] = (byte) (values[1] >>> 36);
    buf[off++] = (byte) (values[1] >>> 28);
    buf[off++] = (byte) (values[1] >>> 20);
    buf[off++] = (byte) (values[1] >>> 12);
    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 54;
    buf[off++] = (byte) (values[2] >>> 46);
    buf[off++] = (byte) (values[2] >>> 38);
    buf[off++] = (byte) (values[2] >>> 30);
    buf[off++] = (byte) (values[2] >>> 22);
    buf[off++] = (byte) (values[2] >>> 14);
    buf[off++] = (byte) (values[2] >>> 6);

    buf[off] = (byte) (values[2] << 2);
    buf[off++] |= values[3] >>> 56;
    buf[off++] = (byte) (values[3] >>> 48);
    buf[off++] = (byte) (values[3] >>> 40);
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 50);
    buf[off++] = (byte) (values[4] >>> 42);
    buf[off++] = (byte) (values[4] >>> 34);
    buf[off++] = (byte) (values[4] >>> 26);
    buf[off++] = (byte) (values[4] >>> 18);
    buf[off++] = (byte) (values[4] >>> 10);
    buf[off++] = (byte) (values[4] >>> 2);

    buf[off] = (byte) (values[4] << 6);
    buf[off++] |= values[5] >>> 52;
    buf[off++] = (byte) (values[5] >>> 44);
    buf[off++] = (byte) (values[5] >>> 36);
    buf[off++] = (byte) (values[5] >>> 28);
    buf[off++] = (byte) (values[5] >>> 20);
    buf[off++] = (byte) (values[5] >>> 12);
    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 54;
    buf[off++] = (byte) (values[6] >>> 46);
    buf[off++] = (byte) (values[6] >>> 38);
    buf[off++] = (byte) (values[6] >>> 30);
    buf[off++] = (byte) (values[6] >>> 22);
    buf[off++] = (byte) (values[6] >>> 14);
    buf[off++] = (byte) (values[6] >>> 6);

    buf[off] = (byte) (values[6] << 2);
    buf[off++] |= values[7] >>> 56;
    buf[off++] = (byte) (values[7] >>> 48);
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits59(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 51);
    buf[off++] = (byte) (values[0] >>> 43);
    buf[off++] = (byte) (values[0] >>> 35);
    buf[off++] = (byte) (values[0] >>> 27);
    buf[off++] = (byte) (values[0] >>> 19);
    buf[off++] = (byte) (values[0] >>> 11);
    buf[off++] = (byte) (values[0] >>> 3);

    buf[off] = (byte) (values[0] << 5);
    buf[off++] |= values[1] >>> 54;
    buf[off++] = (byte) (values[1] >>> 46);
    buf[off++] = (byte) (values[1] >>> 38);
    buf[off++] = (byte) (values[1] >>> 30);
    buf[off++] = (byte) (values[1] >>> 22);
    buf[off++] = (byte) (values[1] >>> 14);
    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 57;
    buf[off++] = (byte) (values[2] >>> 49);
    buf[off++] = (byte) (values[2] >>> 41);
    buf[off++] = (byte) (values[2] >>> 33);
    buf[off++] = (byte) (values[2] >>> 25);
    buf[off++] = (byte) (values[2] >>> 17);
    buf[off++] = (byte) (values[2] >>> 9);
    buf[off++] = (byte) (values[2] >>> 1);

    buf[off] = (byte) (values[2] << 7);
    buf[off++] |= values[3] >>> 52;
    buf[off++] = (byte) (values[3] >>> 44);
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 55;
    buf[off++] = (byte) (values[4] >>> 47);
    buf[off++] = (byte) (values[4] >>> 39);
    buf[off++] = (byte) (values[4] >>> 31);
    buf[off++] = (byte) (values[4] >>> 23);
    buf[off++] = (byte) (values[4] >>> 15);
    buf[off++] = (byte) (values[4] >>> 7);

    buf[off] = (byte) (values[4] << 1);
    buf[off++] |= values[5] >>> 58;
    buf[off++] = (byte) (values[5] >>> 50);
    buf[off++] = (byte) (values[5] >>> 42);
    buf[off++] = (byte) (values[5] >>> 34);
    buf[off++] = (byte) (values[5] >>> 26);
    buf[off++] = (byte) (values[5] >>> 18);
    buf[off++] = (byte) (values[5] >>> 10);
    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 53;
    buf[off++] = (byte) (values[6] >>> 45);
    buf[off++] = (byte) (values[6] >>> 37);
    buf[off++] = (byte) (values[6] >>> 29);
    buf[off++] = (byte) (values[6] >>> 21);
    buf[off++] = (byte) (values[6] >>> 13);
    buf[off++] = (byte) (values[6] >>> 5);

    buf[off] = (byte) (values[6] << 3);
    buf[off++] |= values[7] >>> 56;
    buf[off++] = (byte) (values[7] >>> 48);
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits60(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 52);
    buf[off++] = (byte) (values[0] >>> 44);
    buf[off++] = (byte) (values[0] >>> 36);
    buf[off++] = (byte) (values[0] >>> 28);
    buf[off++] = (byte) (values[0] >>> 20);
    buf[off++] = (byte) (values[0] >>> 12);
    buf[off++] = (byte) (values[0] >>> 4);

    buf[off] = (byte) (values[0] << 4);
    buf[off++] |= values[1] >>> 56;
    buf[off++] = (byte) (values[1] >>> 48);
    buf[off++] = (byte) (values[1] >>> 40);
    buf[off++] = (byte) (values[1] >>> 32);
    buf[off++] = (byte) (values[1] >>> 24);
    buf[off++] = (byte) (values[1] >>> 16);
    buf[off++] = (byte) (values[1] >>> 8);
    buf[off++] = (byte) (values[1]);

    buf[off++] = (byte) (values[2] >>> 52);
    buf[off++] = (byte) (values[2] >>> 44);
    buf[off++] = (byte) (values[2] >>> 36);
    buf[off++] = (byte) (values[2] >>> 28);
    buf[off++] = (byte) (values[2] >>> 20);
    buf[off++] = (byte) (values[2] >>> 12);
    buf[off++] = (byte) (values[2] >>> 4);

    buf[off] = (byte) (values[2] << 4);
    buf[off++] |= values[3] >>> 56;
    buf[off++] = (byte) (values[3] >>> 48);
    buf[off++] = (byte) (values[3] >>> 40);
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 52);
    buf[off++] = (byte) (values[4] >>> 44);
    buf[off++] = (byte) (values[4] >>> 36);
    buf[off++] = (byte) (values[4] >>> 28);
    buf[off++] = (byte) (values[4] >>> 20);
    buf[off++] = (byte) (values[4] >>> 12);
    buf[off++] = (byte) (values[4] >>> 4);

    buf[off] = (byte) (values[4] << 4);
    buf[off++] |= values[5] >>> 56;
    buf[off++] = (byte) (values[5] >>> 48);
    buf[off++] = (byte) (values[5] >>> 40);
    buf[off++] = (byte) (values[5] >>> 32);
    buf[off++] = (byte) (values[5] >>> 24);
    buf[off++] = (byte) (values[5] >>> 16);
    buf[off++] = (byte) (values[5] >>> 8);
    buf[off++] = (byte) (values[5]);

    buf[off++] = (byte) (values[6] >>> 52);
    buf[off++] = (byte) (values[6] >>> 44);
    buf[off++] = (byte) (values[6] >>> 36);
    buf[off++] = (byte) (values[6] >>> 28);
    buf[off++] = (byte) (values[6] >>> 20);
    buf[off++] = (byte) (values[6] >>> 12);
    buf[off++] = (byte) (values[6] >>> 4);

    buf[off] = (byte) (values[6] << 4);
    buf[off++] |= values[7] >>> 56;
    buf[off++] = (byte) (values[7] >>> 48);
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits61(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 53);
    buf[off++] = (byte) (values[0] >>> 45);
    buf[off++] = (byte) (values[0] >>> 37);
    buf[off++] = (byte) (values[0] >>> 29);
    buf[off++] = (byte) (values[0] >>> 21);
    buf[off++] = (byte) (values[0] >>> 13);
    buf[off++] = (byte) (values[0] >>> 5);

    buf[off] = (byte) (values[0] << 3);
    buf[off++] |= values[1] >>> 58;
    buf[off++] = (byte) (values[1] >>> 50);
    buf[off++] = (byte) (values[1] >>> 42);
    buf[off++] = (byte) (values[1] >>> 34);
    buf[off++] = (byte) (values[1] >>> 26);
    buf[off++] = (byte) (values[1] >>> 18);
    buf[off++] = (byte) (values[1] >>> 10);
    buf[off++] = (byte) (values[1] >>> 2);

    buf[off] = (byte) (values[1] << 6);
    buf[off++] |= values[2] >>> 55;
    buf[off++] = (byte) (values[2] >>> 47);
    buf[off++] = (byte) (values[2] >>> 39);
    buf[off++] = (byte) (values[2] >>> 31);
    buf[off++] = (byte) (values[2] >>> 23);
    buf[off++] = (byte) (values[2] >>> 15);
    buf[off++] = (byte) (values[2] >>> 7);

    buf[off] = (byte) (values[2] << 1);
    buf[off++] |= values[3] >>> 60;
    buf[off++] = (byte) (values[3] >>> 52);
    buf[off++] = (byte) (values[3] >>> 44);
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 57;
    buf[off++] = (byte) (values[4] >>> 49);
    buf[off++] = (byte) (values[4] >>> 41);
    buf[off++] = (byte) (values[4] >>> 33);
    buf[off++] = (byte) (values[4] >>> 25);
    buf[off++] = (byte) (values[4] >>> 17);
    buf[off++] = (byte) (values[4] >>> 9);
    buf[off++] = (byte) (values[4] >>> 1);

    buf[off] = (byte) (values[4] << 7);
    buf[off++] |= values[5] >>> 54;
    buf[off++] = (byte) (values[5] >>> 46);
    buf[off++] = (byte) (values[5] >>> 38);
    buf[off++] = (byte) (values[5] >>> 30);
    buf[off++] = (byte) (values[5] >>> 22);
    buf[off++] = (byte) (values[5] >>> 14);
    buf[off++] = (byte) (values[5] >>> 6);

    buf[off] = (byte) (values[5] << 2);
    buf[off++] |= values[6] >>> 59;
    buf[off++] = (byte) (values[6] >>> 51);
    buf[off++] = (byte) (values[6] >>> 43);
    buf[off++] = (byte) (values[6] >>> 35);
    buf[off++] = (byte) (values[6] >>> 27);
    buf[off++] = (byte) (values[6] >>> 19);
    buf[off++] = (byte) (values[6] >>> 11);
    buf[off++] = (byte) (values[6] >>> 3);

    buf[off] = (byte) (values[6] << 5);
    buf[off++] |= values[7] >>> 56;
    buf[off++] = (byte) (values[7] >>> 48);
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits62(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 54);
    buf[off++] = (byte) (values[0] >>> 46);
    buf[off++] = (byte) (values[0] >>> 38);
    buf[off++] = (byte) (values[0] >>> 30);
    buf[off++] = (byte) (values[0] >>> 22);
    buf[off++] = (byte) (values[0] >>> 14);
    buf[off++] = (byte) (values[0] >>> 6);

    buf[off] = (byte) (values[0] << 2);
    buf[off++] |= values[1] >>> 60;
    buf[off++] = (byte) (values[1] >>> 52);
    buf[off++] = (byte) (values[1] >>> 44);
    buf[off++] = (byte) (values[1] >>> 36);
    buf[off++] = (byte) (values[1] >>> 28);
    buf[off++] = (byte) (values[1] >>> 20);
    buf[off++] = (byte) (values[1] >>> 12);
    buf[off++] = (byte) (values[1] >>> 4);

    buf[off] = (byte) (values[1] << 4);
    buf[off++] |= values[2] >>> 58;
    buf[off++] = (byte) (values[2] >>> 50);
    buf[off++] = (byte) (values[2] >>> 42);
    buf[off++] = (byte) (values[2] >>> 34);
    buf[off++] = (byte) (values[2] >>> 26);
    buf[off++] = (byte) (values[2] >>> 18);
    buf[off++] = (byte) (values[2] >>> 10);
    buf[off++] = (byte) (values[2] >>> 2);

    buf[off] = (byte) (values[2] << 6);
    buf[off++] |= values[3] >>> 56;
    buf[off++] = (byte) (values[3] >>> 48);
    buf[off++] = (byte) (values[3] >>> 40);
    buf[off++] = (byte) (values[3] >>> 32);
    buf[off++] = (byte) (values[3] >>> 24);
    buf[off++] = (byte) (values[3] >>> 16);
    buf[off++] = (byte) (values[3] >>> 8);
    buf[off++] = (byte) (values[3]);

    buf[off++] = (byte) (values[4] >>> 54);
    buf[off++] = (byte) (values[4] >>> 46);
    buf[off++] = (byte) (values[4] >>> 38);
    buf[off++] = (byte) (values[4] >>> 30);
    buf[off++] = (byte) (values[4] >>> 22);
    buf[off++] = (byte) (values[4] >>> 14);
    buf[off++] = (byte) (values[4] >>> 6);

    buf[off] = (byte) (values[4] << 2);
    buf[off++] |= values[5] >>> 60;
    buf[off++] = (byte) (values[5] >>> 52);
    buf[off++] = (byte) (values[5] >>> 44);
    buf[off++] = (byte) (values[5] >>> 36);
    buf[off++] = (byte) (values[5] >>> 28);
    buf[off++] = (byte) (values[5] >>> 20);
    buf[off++] = (byte) (values[5] >>> 12);
    buf[off++] = (byte) (values[5] >>> 4);

    buf[off] = (byte) (values[5] << 4);
    buf[off++] |= values[6] >>> 58;
    buf[off++] = (byte) (values[6] >>> 50);
    buf[off++] = (byte) (values[6] >>> 42);
    buf[off++] = (byte) (values[6] >>> 34);
    buf[off++] = (byte) (values[6] >>> 26);
    buf[off++] = (byte) (values[6] >>> 18);
    buf[off++] = (byte) (values[6] >>> 10);
    buf[off++] = (byte) (values[6] >>> 2);

    buf[off] = (byte) (values[6] << 6);
    buf[off++] |= values[7] >>> 56;
    buf[off++] = (byte) (values[7] >>> 48);
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) (values[7]);
  }

  static void packBits63(long[] values, int i, byte[] buf, int off) {
    buf[off++] = (byte) (values[0] >>> 55);
    buf[off++] = (byte) (values[0] >>> 47);
    buf[off++] = (byte) (values[0] >>> 39);
    buf[off++] = (byte) (values[0] >>> 31);
    buf[off++] = (byte) (values[0] >>> 23);
    buf[off++] = (byte) (values[0] >>> 15);
    buf[off++] = (byte) (values[0] >>> 7);

    buf[off] = (byte) (values[0] << 1);
    buf[off++] |= values[1] >>> 62;
    buf[off++] = (byte) (values[1] >>> 54);
    buf[off++] = (byte) (values[1] >>> 46);
    buf[off++] = (byte) (values[1] >>> 38);
    buf[off++] = (byte) (values[1] >>> 30);
    buf[off++] = (byte) (values[1] >>> 22);
    buf[off++] = (byte) (values[1] >>> 14);
    buf[off++] = (byte) (values[1] >>> 6);

    buf[off] = (byte) (values[1] << 2);
    buf[off++] |= values[2] >>> 61;
    buf[off++] = (byte) (values[2] >>> 53);
    buf[off++] = (byte) (values[2] >>> 45);
    buf[off++] = (byte) (values[2] >>> 37);
    buf[off++] = (byte) (values[2] >>> 29);
    buf[off++] = (byte) (values[2] >>> 21);
    buf[off++] = (byte) (values[2] >>> 13);
    buf[off++] = (byte) (values[2] >>> 5);

    buf[off] = (byte) (values[2] << 3);
    buf[off++] |= values[3] >>> 60;
    buf[off++] = (byte) (values[3] >>> 52);
    buf[off++] = (byte) (values[3] >>> 44);
    buf[off++] = (byte) (values[3] >>> 36);
    buf[off++] = (byte) (values[3] >>> 28);
    buf[off++] = (byte) (values[3] >>> 20);
    buf[off++] = (byte) (values[3] >>> 12);
    buf[off++] = (byte) (values[3] >>> 4);

    buf[off] = (byte) (values[3] << 4);
    buf[off++] |= values[4] >>> 59;
    buf[off++] = (byte) (values[4] >>> 51);
    buf[off++] = (byte) (values[4] >>> 43);
    buf[off++] = (byte) (values[4] >>> 35);
    buf[off++] = (byte) (values[4] >>> 27);
    buf[off++] = (byte) (values[4] >>> 19);
    buf[off++] = (byte) (values[4] >>> 11);
    buf[off++] = (byte) (values[4] >>> 3);

    buf[off] = (byte) (values[4] << 5);
    buf[off++] |= values[5] >>> 58;
    buf[off++] = (byte) (values[5] >>> 50);
    buf[off++] = (byte) (values[5] >>> 42);
    buf[off++] = (byte) (values[5] >>> 34);
    buf[off++] = (byte) (values[5] >>> 26);
    buf[off++] = (byte) (values[5] >>> 18);
    buf[off++] = (byte) (values[5] >>> 10);
    buf[off++] = (byte) (values[5] >>> 2);

    buf[off] = (byte) (values[5] << 6);
    buf[off++] |= values[6] >>> 57;
    buf[off++] = (byte) (values[6] >>> 49);
    buf[off++] = (byte) (values[6] >>> 41);
    buf[off++] = (byte) (values[6] >>> 33);
    buf[off++] = (byte) (values[6] >>> 25);
    buf[off++] = (byte) (values[6] >>> 17);
    buf[off++] = (byte) (values[6] >>> 9);
    buf[off++] = (byte) (values[6] >>> 1);

    buf[off] = (byte) (values[6] << 7);
    buf[off++] |= values[7] >>> 56;
    buf[off++] = (byte) (values[7] >>> 48);
    buf[off++] = (byte) (values[7] >>> 40);
    buf[off++] = (byte) (values[7] >>> 32);
    buf[off++] = (byte) (values[7] >>> 24);
    buf[off++] = (byte) (values[7] >>> 16);
    buf[off++] = (byte) (values[7] >>> 8);
    buf[off] = (byte) values[7];
  }

  static void unpackBits1(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off]) >>> 7) & 1;
    values[i + 1] = (Byte.toUnsignedLong(buf[off]) >>> 6) & 1;
    values[i + 2] = (Byte.toUnsignedLong(buf[off]) >>> 5) & 1;
    values[i + 3] = (Byte.toUnsignedLong(buf[off]) >>> 4) & 1;
    values[i + 4] = (Byte.toUnsignedLong(buf[off]) >>> 3) & 1;
    values[i + 5] = (Byte.toUnsignedLong(buf[off]) >>> 2) & 1;
    values[i + 6] = (Byte.toUnsignedLong(buf[off]) >>> 1) & 1;
    values[i + 7] = Byte.toUnsignedLong(buf[off]) & 1;
  }

  static void unpackBits2(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off]) >>> 6) & 3;
    values[i + 1] = (Byte.toUnsignedLong(buf[off]) >>> 4) & 3;
    values[i + 2] = (Byte.toUnsignedLong(buf[off]) >>> 2) & 3;
    values[i + 3] = Byte.toUnsignedLong(buf[off++]) & 3;
    values[i + 4] = (Byte.toUnsignedLong(buf[off]) >>> 6) & 3;
    values[i + 5] = (Byte.toUnsignedLong(buf[off]) >>> 4) & 3;
    values[i + 6] = (Byte.toUnsignedLong(buf[off]) >>> 2) & 3;
    values[i + 7] = Byte.toUnsignedLong(buf[off]) & 3;
  }

  static void unpackBits3(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off]) >>> 5;
    values[i + 1] = (Byte.toUnsignedLong(buf[off]) >>> 2) & 7;
    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 1;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 7;
    values[i + 3] = (Byte.toUnsignedLong(buf[off]) >>> 4) & 7;
    values[i + 4] = (Byte.toUnsignedLong(buf[off]) >>> 1) & 7;
    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 1) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;
    values[i + 6] = (Byte.toUnsignedLong(buf[off]) >>> 3) & 7;
    values[i + 7] = Byte.toUnsignedLong(buf[off]) & 7;
  }

  static void unpackBits4(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off]) >>> 4;
    values[i + 1] = Byte.toUnsignedLong(buf[off++]) & 0xf;
    values[i + 2] = Byte.toUnsignedLong(buf[off]) >>> 4;
    values[i + 3] = Byte.toUnsignedLong(buf[off++]) & 0xf;
    values[i + 4] = Byte.toUnsignedLong(buf[off]) >>> 4;
    values[i + 5] = Byte.toUnsignedLong(buf[off++]) & 0xf;
    values[i + 6] = Byte.toUnsignedLong(buf[off]) >>> 4;
    values[i + 7] = Byte.toUnsignedLong(buf[off]) & 0xf;
  }

  static void unpackBits5(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 7) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off]) >>> 1) & 0x1f;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 1) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 1;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 5] = (Byte.toUnsignedLong(buf[off]) >>> 2) & 0x1f;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 3;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 7] = Byte.toUnsignedLong(buf[off]) & 0x1f;
  }

  static void unpackBits6(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 3) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 2;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 3] = Byte.toUnsignedLong(buf[off++]) & 0x3f;

    values[i + 4] = Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 3) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 2;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 7] = Byte.toUnsignedLong(buf[off]) & 0x3f;
  }

  static void unpackBits7(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 1) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 5;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 7) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 3;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 1;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 7] = Byte.toUnsignedLong(buf[off]) & 0x7f;
  }

  static void unpackBits8(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]);
    values[i + 1] = Byte.toUnsignedLong(buf[off++]);
    values[i + 2] = Byte.toUnsignedLong(buf[off++]);
    values[i + 3] = Byte.toUnsignedLong(buf[off++]);
    values[i + 4] = Byte.toUnsignedLong(buf[off++]);
    values[i + 5] = Byte.toUnsignedLong(buf[off++]);
    values[i + 6] = Byte.toUnsignedLong(buf[off++]);
    values[i + 7] = Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits9(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 3;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 5;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 7) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 7;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 1) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits10(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 6;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 3) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 6;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 3) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits11(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 9;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 7;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 1) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 5;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 7) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits12(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 2] = Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 6] = Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits13(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 7) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 7;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 1) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 9;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 11;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits14(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 3) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 10;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 3) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 10;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits15(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 1) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 13;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 7) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 11;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 9;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits16(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 1] = Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 2] = Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 3] = Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 5] = Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 6] = Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 7] = Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits17(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 11;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 13;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 7) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 15;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 1) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits18(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 14;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 3) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 14;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 3) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits19(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 17;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 15;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 1) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 13;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 7) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits20(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 2] = Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 6] = Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits21(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 7) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 15;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 1) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 17;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 19;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits22(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 3) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 18;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 3) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 18;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits23(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 1) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 21;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 7) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 19;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 17;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits24(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 1] = Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 2] = Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 3] = Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 5] = Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 6] = Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 7] = Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits25(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 19;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 21;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 7) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 23;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 1) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits26(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 22;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 3) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 22;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 3) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits27(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 25;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 23;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 1) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 21;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 7) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits28(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 24;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 2] = Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 24;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 6] = Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits29(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 7) << 26;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 23;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 1) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 25;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 27;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits30(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 3) << 28;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 26;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 3) << 28;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 26;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits31(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 1) << 30;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 29;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 7) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 27;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 25;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits32(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 1] = (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 2] = (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 3] = (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 5] = (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 6] = (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 7] = (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits33(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 26;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 27;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 29;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 7) << 30;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 31;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 1) << 32;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits34(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 28;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 30;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 3) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 28;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 30;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 3) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]);
  }

  static void unpackBits35(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 30;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 2) << 33;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 31;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 1) << 34;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 29;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 7) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits36(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 32;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 2] = (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 32;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 6] = (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits37(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 7) << 34;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 31;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 23;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 1) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 33;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 30;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 35;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits38(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 3) << 36;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 34;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 3) << 36;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 34;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits39(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 1) << 38;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 37;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 7) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 35;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 34;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 33;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits40(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 1] = (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 2] = (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 3] = (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 5] = (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 6] = (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 7] = (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits41(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 34;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 35;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 37;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 7) << 38;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 39;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 1) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits42(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 36;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 38;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 3) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 36;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 38;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 3) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits43(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 38;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 41;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 39;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 1) << 42;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 37;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 7) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits44(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 40;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 2] = (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 40;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 6] = (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits45(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 7) << 42;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 39;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 23;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 1) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 41;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 38;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 43;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits46(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 3) << 44;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 42;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 3) << 44;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 42;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits47(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 39;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 1) << 46;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 45;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 7) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 43;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 42;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 41;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits48(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 1] = (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 2] = (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 3] = (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 5] = (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 6] = (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 7] = (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits49(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 41;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 42;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 43;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 45;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 7) << 46;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 47;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 39;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 1) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits50(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 44;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 46;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 3) << 48;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 44;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 46;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 3) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits51(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 43;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 46;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 49;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 41;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 47;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 39;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 1) << 50;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 45;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 7) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits52(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 48;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 2] = (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 48;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 48;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 6] = (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits53(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 45;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 7) << 50;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 47;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 39;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 23;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 1) << 52;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 49;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 41;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 46;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 51;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 43;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits54(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 3) << 52;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 50;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 48;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 3) << 52;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 50;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]);
  }

  static void unpackBits55(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 47;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 39;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 1) << 54;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 53;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 45;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 7) << 52;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 51;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 43;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 50;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 49;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 41;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits56(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 1] = (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 2] = (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 3] = (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 5] = (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 6] = (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]);
    values[i + 7] = (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits57(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 49;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 41;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 50;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 51;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 43;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 52;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 53;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 45;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 7) << 54;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 55;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 47;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 39;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 1) << 56;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits58(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 50;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 52;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 54;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 3) << 56;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 50;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 52;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 54;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 3) << 56;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]);
  }

  static void unpackBits59(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 51;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 43;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 54;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 57;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 49;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 41;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 52;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 55;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 47;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 39;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 1) << 58;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 50;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 53;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 45;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 7) << 56;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits60(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 52;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 56;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 2] = (Byte.toUnsignedLong(buf[off++])) << 52;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 56;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 52;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 56;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 6] = (Byte.toUnsignedLong(buf[off++])) << 52;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 56;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits61(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 53;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 45;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 7) << 58;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 50;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 55;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 47;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 39;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 1) << 60;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 52;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 57;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 49;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 41;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 54;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 3) << 59;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 51;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 43;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 56;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits62(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 54;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 3) << 60;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 52;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 58;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 50;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 56;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]);

    values[i + 4] = (Byte.toUnsignedLong(buf[off++])) << 54;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 3) << 60;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 52;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 58;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 50;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 56;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

  static void unpackBits63(long[] values, int i, byte[] buf, int off) {
    values[i + 0] = (Byte.toUnsignedLong(buf[off++])) << 55;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 47;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 39;
    values[i + 0] |= (Byte.toUnsignedLong(buf[off++])) << 31;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 23;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 15;
    values[i + 0] |= Byte.toUnsignedLong(buf[off++]) << 7;
    values[i + 0] |= Byte.toUnsignedLong(buf[off]) >>> 1;

    values[i + 1] = (Byte.toUnsignedLong(buf[off++]) & 1) << 62;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 54;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 46;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 38;
    values[i + 1] |= (Byte.toUnsignedLong(buf[off++])) << 30;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 22;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 14;
    values[i + 1] |= Byte.toUnsignedLong(buf[off++]) << 6;
    values[i + 1] |= Byte.toUnsignedLong(buf[off]) >>> 2;

    values[i + 2] = (Byte.toUnsignedLong(buf[off++]) & 3) << 61;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 53;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 45;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 37;
    values[i + 2] |= (Byte.toUnsignedLong(buf[off++])) << 29;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 21;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 13;
    values[i + 2] |= Byte.toUnsignedLong(buf[off++]) << 5;
    values[i + 2] |= Byte.toUnsignedLong(buf[off]) >>> 3;

    values[i + 3] = (Byte.toUnsignedLong(buf[off++]) & 7) << 60;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 52;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 44;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 36;
    values[i + 3] |= (Byte.toUnsignedLong(buf[off++])) << 28;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 20;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 12;
    values[i + 3] |= Byte.toUnsignedLong(buf[off++]) << 4;
    values[i + 3] |= Byte.toUnsignedLong(buf[off]) >>> 4;

    values[i + 4] = (Byte.toUnsignedLong(buf[off++]) & 0xf) << 59;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 51;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 43;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 35;
    values[i + 4] |= (Byte.toUnsignedLong(buf[off++])) << 27;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 19;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 11;
    values[i + 4] |= Byte.toUnsignedLong(buf[off++]) << 3;
    values[i + 4] |= Byte.toUnsignedLong(buf[off]) >>> 5;

    values[i + 5] = (Byte.toUnsignedLong(buf[off++]) & 0x1f) << 58;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 50;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 42;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 34;
    values[i + 5] |= (Byte.toUnsignedLong(buf[off++])) << 26;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 18;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 10;
    values[i + 5] |= Byte.toUnsignedLong(buf[off++]) << 2;
    values[i + 5] |= Byte.toUnsignedLong(buf[off]) >>> 6;

    values[i + 6] = (Byte.toUnsignedLong(buf[off++]) & 0x3f) << 57;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 49;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 41;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 33;
    values[i + 6] |= (Byte.toUnsignedLong(buf[off++])) << 25;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 17;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 9;
    values[i + 6] |= Byte.toUnsignedLong(buf[off++]) << 1;
    values[i + 6] |= Byte.toUnsignedLong(buf[off]) >>> 7;

    values[i + 7] = (Byte.toUnsignedLong(buf[off++]) & 0x7f) << 56;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 48;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 40;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 32;
    values[i + 7] |= (Byte.toUnsignedLong(buf[off++])) << 24;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 16;
    values[i + 7] |= Byte.toUnsignedLong(buf[off++]) << 8;
    values[i + 7] |= Byte.toUnsignedLong(buf[off]);
  }

}
