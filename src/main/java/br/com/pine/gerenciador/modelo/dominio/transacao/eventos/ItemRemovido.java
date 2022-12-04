// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/eventos_transacao.proto

package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

/**
 * Protobuf type {@code transacao.eventos.ItemRemovido}
 */
public final class ItemRemovido extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:transacao.eventos.ItemRemovido)
    ItemRemovidoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ItemRemovido.newBuilder() to construct.
  private ItemRemovido(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ItemRemovido() {
    descricao_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ItemRemovido();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemRemovido_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemRemovido_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido.class, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido.Builder.class);
  }

  private int bitField0_;
  public static final int DESCRICAO_FIELD_NUMBER = 1;
  private volatile java.lang.Object descricao_;
  /**
   * <code>optional string descricao = 1;</code>
   * @return Whether the descricao field is set.
   */
  @java.lang.Override
  public boolean hasDescricao() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>optional string descricao = 1;</code>
   * @return The descricao.
   */
  @java.lang.Override
  public java.lang.String getDescricao() {
    java.lang.Object ref = descricao_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      descricao_ = s;
      return s;
    }
  }
  /**
   * <code>optional string descricao = 1;</code>
   * @return The bytes for descricao.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDescricaoBytes() {
    java.lang.Object ref = descricao_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      descricao_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, descricao_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, descricao_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido)) {
      return super.equals(obj);
    }
    br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido other = (br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido) obj;

    if (hasDescricao() != other.hasDescricao()) return false;
    if (hasDescricao()) {
      if (!getDescricao()
          .equals(other.getDescricao())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasDescricao()) {
      hash = (37 * hash) + DESCRICAO_FIELD_NUMBER;
      hash = (53 * hash) + getDescricao().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code transacao.eventos.ItemRemovido}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:transacao.eventos.ItemRemovido)
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovidoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemRemovido_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemRemovido_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido.class, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido.Builder.class);
    }

    // Construct using br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      descricao_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemRemovido_descriptor;
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido getDefaultInstanceForType() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido.getDefaultInstance();
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido build() {
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido buildPartial() {
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido result = new br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        to_bitField0_ |= 0x00000001;
      }
      result.descricao_ = descricao_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido) {
        return mergeFrom((br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido other) {
      if (other == br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido.getDefaultInstance()) return this;
      if (other.hasDescricao()) {
        bitField0_ |= 0x00000001;
        descricao_ = other.descricao_;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              descricao_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private java.lang.Object descricao_ = "";
    /**
     * <code>optional string descricao = 1;</code>
     * @return Whether the descricao field is set.
     */
    public boolean hasDescricao() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional string descricao = 1;</code>
     * @return The descricao.
     */
    public java.lang.String getDescricao() {
      java.lang.Object ref = descricao_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        descricao_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string descricao = 1;</code>
     * @return The bytes for descricao.
     */
    public com.google.protobuf.ByteString
        getDescricaoBytes() {
      java.lang.Object ref = descricao_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        descricao_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string descricao = 1;</code>
     * @param value The descricao to set.
     * @return This builder for chaining.
     */
    public Builder setDescricao(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      descricao_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string descricao = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDescricao() {
      bitField0_ = (bitField0_ & ~0x00000001);
      descricao_ = getDefaultInstance().getDescricao();
      onChanged();
      return this;
    }
    /**
     * <code>optional string descricao = 1;</code>
     * @param value The bytes for descricao to set.
     * @return This builder for chaining.
     */
    public Builder setDescricaoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      bitField0_ |= 0x00000001;
      descricao_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:transacao.eventos.ItemRemovido)
  }

  // @@protoc_insertion_point(class_scope:transacao.eventos.ItemRemovido)
  private static final br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido();
  }

  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ItemRemovido>
      PARSER = new com.google.protobuf.AbstractParser<ItemRemovido>() {
    @java.lang.Override
    public ItemRemovido parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<ItemRemovido> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ItemRemovido> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemRemovido getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
