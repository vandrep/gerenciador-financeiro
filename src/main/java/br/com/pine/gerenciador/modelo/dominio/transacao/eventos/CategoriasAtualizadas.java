// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: eventos_transacao.proto

package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

import br.com.pine.gerenciador.modelo.dominio.Evento;

/**
 * Protobuf type {@code transacao.eventos.CategoriasAtualizadas}
 */
public final class CategoriasAtualizadas extends
    com.google.protobuf.GeneratedMessageV3 implements Evento,
    // @@protoc_insertion_point(message_implements:transacao.eventos.CategoriasAtualizadas)
    CategoriasAtualizadasOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CategoriasAtualizadas.newBuilder() to construct.
  private CategoriasAtualizadas(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CategoriasAtualizadas() {
    categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CategoriasAtualizadas();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_CategoriasAtualizadas_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_CategoriasAtualizadas_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas.class, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas.Builder.class);
  }

  public static final int CATEGORIAS_FIELD_NUMBER = 1;
  private com.google.protobuf.LazyStringList categorias_;
  /**
   * <code>repeated string categorias = 1;</code>
   * @return A list containing the categorias.
   */
  public com.google.protobuf.ProtocolStringList
      getCategoriasList() {
    return categorias_;
  }
  /**
   * <code>repeated string categorias = 1;</code>
   * @return The count of categorias.
   */
  public int getCategoriasCount() {
    return categorias_.size();
  }
  /**
   * <code>repeated string categorias = 1;</code>
   * @param index The index of the element to return.
   * @return The categorias at the given index.
   */
  public java.lang.String getCategorias(int index) {
    return categorias_.get(index);
  }
  /**
   * <code>repeated string categorias = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the categorias at the given index.
   */
  public com.google.protobuf.ByteString
      getCategoriasBytes(int index) {
    return categorias_.getByteString(index);
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
    for (int i = 0; i < categorias_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, categorias_.getRaw(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < categorias_.size(); i++) {
        dataSize += computeStringSizeNoTag(categorias_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getCategoriasList().size();
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
    if (!(obj instanceof br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas)) {
      return super.equals(obj);
    }
    br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas other = (br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas) obj;

    if (!getCategoriasList()
        .equals(other.getCategoriasList())) return false;
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
    if (getCategoriasCount() > 0) {
      hash = (37 * hash) + CATEGORIAS_FIELD_NUMBER;
      hash = (53 * hash) + getCategoriasList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas parseFrom(
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
  public static Builder newBuilder(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas prototype) {
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
   * Protobuf type {@code transacao.eventos.CategoriasAtualizadas}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:transacao.eventos.CategoriasAtualizadas)
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadasOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_CategoriasAtualizadas_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_CategoriasAtualizadas_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas.class, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas.Builder.class);
    }

    // Construct using br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_CategoriasAtualizadas_descriptor;
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas getDefaultInstanceForType() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas.getDefaultInstance();
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas build() {
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas buildPartial() {
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas result = new br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        categorias_ = categorias_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.categorias_ = categorias_;
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
      if (other instanceof br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas) {
        return mergeFrom((br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas other) {
      if (other == br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas.getDefaultInstance()) return this;
      if (!other.categorias_.isEmpty()) {
        if (categorias_.isEmpty()) {
          categorias_ = other.categorias_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureCategoriasIsMutable();
          categorias_.addAll(other.categorias_);
        }
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
              java.lang.String s = input.readStringRequireUtf8();
              ensureCategoriasIsMutable();
              categorias_.add(s);
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

    private com.google.protobuf.LazyStringList categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureCategoriasIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        categorias_ = new com.google.protobuf.LazyStringArrayList(categorias_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string categorias = 1;</code>
     * @return A list containing the categorias.
     */
    public com.google.protobuf.ProtocolStringList
        getCategoriasList() {
      return categorias_.getUnmodifiableView();
    }
    /**
     * <code>repeated string categorias = 1;</code>
     * @return The count of categorias.
     */
    public int getCategoriasCount() {
      return categorias_.size();
    }
    /**
     * <code>repeated string categorias = 1;</code>
     * @param index The index of the element to return.
     * @return The categorias at the given index.
     */
    public java.lang.String getCategorias(int index) {
      return categorias_.get(index);
    }
    /**
     * <code>repeated string categorias = 1;</code>
     * @param index The index of the value to return.
     * @return The bytes of the categorias at the given index.
     */
    public com.google.protobuf.ByteString
        getCategoriasBytes(int index) {
      return categorias_.getByteString(index);
    }
    /**
     * <code>repeated string categorias = 1;</code>
     * @param index The index to set the value at.
     * @param value The categorias to set.
     * @return This builder for chaining.
     */
    public Builder setCategorias(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureCategoriasIsMutable();
      categorias_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string categorias = 1;</code>
     * @param value The categorias to add.
     * @return This builder for chaining.
     */
    public Builder addCategorias(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureCategoriasIsMutable();
      categorias_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string categorias = 1;</code>
     * @param values The categorias to add.
     * @return This builder for chaining.
     */
    public Builder addAllCategorias(
        java.lang.Iterable<java.lang.String> values) {
      ensureCategoriasIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, categorias_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string categorias = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCategorias() {
      categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string categorias = 1;</code>
     * @param value The bytes of the categorias to add.
     * @return This builder for chaining.
     */
    public Builder addCategoriasBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureCategoriasIsMutable();
      categorias_.add(value);
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


    // @@protoc_insertion_point(builder_scope:transacao.eventos.CategoriasAtualizadas)
  }

  // @@protoc_insertion_point(class_scope:transacao.eventos.CategoriasAtualizadas)
  private static final br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas();
  }

  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CategoriasAtualizadas>
      PARSER = new com.google.protobuf.AbstractParser<CategoriasAtualizadas>() {
    @java.lang.Override
    public CategoriasAtualizadas parsePartialFrom(
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

  public static com.google.protobuf.Parser<CategoriasAtualizadas> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CategoriasAtualizadas> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.CategoriasAtualizadas getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

