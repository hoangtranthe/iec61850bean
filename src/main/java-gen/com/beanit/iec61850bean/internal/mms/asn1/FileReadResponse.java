/*
 * This class file was automatically generated by ASN1bean (http://www.beanit.com)
 */

package com.beanit.iec61850bean.internal.mms.asn1;

import com.beanit.asn1bean.ber.BerLength;
import com.beanit.asn1bean.ber.BerTag;
import com.beanit.asn1bean.ber.ReverseByteArrayOutputStream;
import com.beanit.asn1bean.ber.types.BerOctetString;
import com.beanit.asn1bean.ber.types.BerType;
import com.beanit.iec61850bean.internal.BerBoolean;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class FileReadResponse implements BerType, Serializable {

  public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
  private static final long serialVersionUID = 1L;
  private byte[] code = null;
  private BerOctetString fileData = null;
  private BerBoolean moreFollows = null;

  public FileReadResponse() {}

  public FileReadResponse(byte[] code) {
    this.code = code;
  }

  public BerOctetString getFileData() {
    return fileData;
  }

  public void setFileData(BerOctetString fileData) {
    this.fileData = fileData;
  }

  public BerBoolean getMoreFollows() {
    return moreFollows;
  }

  public void setMoreFollows(BerBoolean moreFollows) {
    this.moreFollows = moreFollows;
  }

  @Override
  public int encode(OutputStream reverseOS) throws IOException {
    return encode(reverseOS, true);
  }

  public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

    if (code != null) {
      reverseOS.write(code);
      if (withTag) {
        return tag.encode(reverseOS) + code.length;
      }
      return code.length;
    }

    int codeLength = 0;
    if (moreFollows != null) {
      codeLength += moreFollows.encode(reverseOS, false);
      // write tag: CONTEXT_CLASS, PRIMITIVE, 1
      reverseOS.write(0x81);
      codeLength += 1;
    }

    codeLength += fileData.encode(reverseOS, false);
    // write tag: CONTEXT_CLASS, PRIMITIVE, 0
    reverseOS.write(0x80);
    codeLength += 1;

    codeLength += BerLength.encodeLength(reverseOS, codeLength);

    if (withTag) {
      codeLength += tag.encode(reverseOS);
    }

    return codeLength;
  }

  @Override
  public int decode(InputStream is) throws IOException {
    return decode(is, true);
  }

  public int decode(InputStream is, boolean withTag) throws IOException {
    int tlByteCount = 0;
    int vByteCount = 0;
    BerTag berTag = new BerTag();

    if (withTag) {
      tlByteCount += tag.decodeAndCheck(is);
    }

    BerLength length = new BerLength();
    tlByteCount += length.decode(is);
    int lengthVal = length.val;
    vByteCount += berTag.decode(is);

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
      fileData = new BerOctetString();
      vByteCount += fileData.decode(is, false);
      if (lengthVal >= 0 && vByteCount == lengthVal) {
        return tlByteCount + vByteCount;
      }
      vByteCount += berTag.decode(is);
    } else {
      throw new IOException("Tag does not match mandatory sequence component.");
    }

    if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
      moreFollows = new BerBoolean();
      vByteCount += moreFollows.decode(is, false);
      if (lengthVal >= 0 && vByteCount == lengthVal) {
        return tlByteCount + vByteCount;
      }
      vByteCount += berTag.decode(is);
    }

    if (lengthVal < 0) {
      if (!berTag.equals(0, 0, 0)) {
        throw new IOException("Decoded sequence has wrong end of contents octets");
      }
      vByteCount += BerLength.readEocByte(is);
      return tlByteCount + vByteCount;
    }

    throw new IOException(
        "Unexpected end of sequence, length tag: " + lengthVal + ", bytes decoded: " + vByteCount);
  }

  public void encodeAndSave(int encodingSizeGuess) throws IOException {
    ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
    encode(reverseOS, false);
    code = reverseOS.getArray();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    appendAsString(sb, 0);
    return sb.toString();
  }

  public void appendAsString(StringBuilder sb, int indentLevel) {

    sb.append("{");
    sb.append("\n");
    for (int i = 0; i < indentLevel + 1; i++) {
      sb.append("\t");
    }
    if (fileData != null) {
      sb.append("fileData: ").append(fileData);
    } else {
      sb.append("fileData: <empty-required-field>");
    }

    if (moreFollows != null) {
      sb.append(",\n");
      for (int i = 0; i < indentLevel + 1; i++) {
        sb.append("\t");
      }
      sb.append("moreFollows: ").append(moreFollows);
    }

    sb.append("\n");
    for (int i = 0; i < indentLevel; i++) {
      sb.append("\t");
    }
    sb.append("}");
  }
}
