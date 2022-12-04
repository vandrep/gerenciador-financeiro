// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/eventos_transacao.proto

package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

/**
 * Protobuf type {@code transacao.eventos.TransacaoCriada}
 */
public final class TransacaoCriada extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:transacao.eventos.TransacaoCriada)
    TransacaoCriadaOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TransacaoCriada.newBuilder() to construct.
  private TransacaoCriada(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TransacaoCriada() {
    id_ = "";
    pagamento_ = "";
    categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TransacaoCriada();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_TransacaoCriada_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_TransacaoCriada_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada.class, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada.Builder.class);
  }

  private int bitField0_;
  public static final int ID_FIELD_NUMBER = 1;
  private volatile java.lang.Object id_;
  /**
   * <code>optional string id = 1;</code>
   * @return Whether the id field is set.
   */
  @java.lang.Override
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>optional string id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public java.lang.String getId() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      id_ = s;
      return s;
    }
  }
  /**
   * <code>optional string id = 1;</code>
   * @return The bytes for id.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getIdBytes() {
    java.lang.Object ref = id_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      id_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PAGAMENTO_FIELD_NUMBER = 2;
  private volatile java.lang.Object pagamento_;
  /**
   * <code>optional string pagamento = 2;</code>
   * @return Whether the pagamento field is set.
   */
  @java.lang.Override
  public boolean hasPagamento() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>optional string pagamento = 2;</code>
   * @return The pagamento.
   */
  @java.lang.Override
  public java.lang.String getPagamento() {
    java.lang.Object ref = pagamento_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      pagamento_ = s;
      return s;
    }
  }
  /**
   * <code>optional string pagamento = 2;</code>
   * @return The bytes for pagamento.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getPagamentoBytes() {
    java.lang.Object ref = pagamento_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      pagamento_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CATEGORIAS_FIELD_NUMBER = 3;
  private com.google.protobuf.LazyStringList categorias_;
  /**
   * <code>repeated string categorias = 3;</code>
   * @return A list containing the categorias.
   */
  public com.google.protobuf.ProtocolStringList
      getCategoriasList() {
    return categorias_;
  }
  /**
   * <code>repeated string categorias = 3;</code>
   * @return The count of categorias.
   */
  public int getCategoriasCount() {
    return categorias_.size();
  }
  /**
   * <code>repeated string categorias = 3;</code>
   * @param index The index of the element to return.
   * @return The categorias at the given index.
   */
  public java.lang.String getCategorias(int index) {
    return categorias_.get(index);
  }
  /**
   * <code>repeated string categorias = 3;</code>
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
    if (((bitField0_ & 0x00000001) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, pagamento_);
    }
    for (int i = 0; i < categorias_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, categorias_.getRaw(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, pagamento_);
    }
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
    if (!(obj instanceof br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada)) {
      return super.equals(obj);
    }
    br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada other = (br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada) obj;

    if (hasId() != other.hasId()) return false;
    if (hasId()) {
      if (!getId()
          .equals(other.getId())) return false;
    }
    if (hasPagamento() != other.hasPagamento()) return false;
    if (hasPagamento()) {
      if (!getPagamento()
          .equals(other.getPagamento())) return false;
    }
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
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId().hashCode();
    }
    if (hasPagamento()) {
      hash = (37 * hash) + PAGAMENTO_FIELD_NUMBER;
      hash = (53 * hash) + getPagamento().hashCode();
    }
    if (getCategoriasCount() > 0) {
      hash = (37 * hash) + CATEGORIAS_FIELD_NUMBER;
      hash = (53 * hash) + getCategoriasList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada parseFrom(
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
  public static Builder newBuilder(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada prototype) {
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
   * Protobuf type {@code transacao.eventos.TransacaoCriada}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:transacao.eventos.TransacaoCriada)
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriadaOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_TransacaoCriada_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_TransacaoCriada_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada.class, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada.Builder.class);
    }

    // Construct using br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      id_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      pagamento_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_TransacaoCriada_descriptor;
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada getDefaultInstanceForType() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada.getDefaultInstance();
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada build() {
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada buildPartial() {
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada result = new br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        to_bitField0_ |= 0x00000001;
      }
      result.id_ = id_;
      if (((from_bitField0_ & 0x00000002) != 0)) {
        to_bitField0_ |= 0x00000002;
      }
      result.pagamento_ = pagamento_;
      if (((bitField0_ & 0x00000004) != 0)) {
        categorias_ = categorias_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000004);
      }
      result.categorias_ = categorias_;
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
      if (other instanceof br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada) {
        return mergeFrom((br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada other) {
      if (other == br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada.getDefaultInstance()) return this;
      if (other.hasId()) {
        bitField0_ |= 0x00000001;
        id_ = other.id_;
        onChanged();
      }
      if (other.hasPagamento()) {
        bitField0_ |= 0x00000002;
        pagamento_ = other.pagamento_;
        onChanged();
      }
      if (!other.categorias_.isEmpty()) {
        if (categorias_.isEmpty()) {
          categorias_ = other.categorias_;
          bitField0_ = (bitField0_ & ~0x00000004);
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
              id_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              pagamento_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();
              ensureCategoriasIsMutable();
              categorias_.add(s);
              break;
            } // case 26
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

    private java.lang.Object id_ = "";
    /**
     * <code>optional string id = 1;</code>
     * @return Whether the id field is set.
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>optional string id = 1;</code>
     * @return The id.
     */
    public java.lang.String getId() {
      java.lang.Object ref = id_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        id_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string id = 1;</code>
     * @return The bytes for id.
     */
    public com.google.protobuf.ByteString
        getIdBytes() {
      java.lang.Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = getDefaultInstance().getId();
      onChanged();
      return this;
    }
    /**
     * <code>optional string id = 1;</code>
     * @param value The bytes for id to set.
     * @return This builder for chaining.
     */
    public Builder setIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object pagamento_ = "";
    /**
     * <code>optional string pagamento = 2;</code>
     * @return Whether the pagamento field is set.
     */
    public boolean hasPagamento() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional string pagamento = 2;</code>
     * @return The pagamento.
     */
    public java.lang.String getPagamento() {
      java.lang.Object ref = pagamento_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        pagamento_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string pagamento = 2;</code>
     * @return The bytes for pagamento.
     */
    public com.google.protobuf.ByteString
        getPagamentoBytes() {
      java.lang.Object ref = pagamento_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        pagamento_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string pagamento = 2;</code>
     * @param value The pagamento to set.
     * @return This builder for chaining.
     */
    public Builder setPagamento(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      pagamento_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string pagamento = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPagamento() {
      bitField0_ = (bitField0_ & ~0x00000002);
      pagamento_ = getDefaultInstance().getPagamento();
      onChanged();
      return this;
    }
    /**
     * <code>optional string pagamento = 2;</code>
     * @param value The bytes for pagamento to set.
     * @return This builder for chaining.
     */
    public Builder setPagamentoBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      bitField0_ |= 0x00000002;
      pagamento_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureCategoriasIsMutable() {
      if (!((bitField0_ & 0x00000004) != 0)) {
        categorias_ = new com.google.protobuf.LazyStringArrayList(categorias_);
        bitField0_ |= 0x00000004;
       }
    }
    /**
     * <code>repeated string categorias = 3;</code>
     * @return A list containing the categorias.
     */
    public com.google.protobuf.ProtocolStringList
        getCategoriasList() {
      return categorias_.getUnmodifiableView();
    }
    /**
     * <code>repeated string categorias = 3;</code>
     * @return The count of categorias.
     */
    public int getCategoriasCount() {
      return categorias_.size();
    }
    /**
     * <code>repeated string categorias = 3;</code>
     * @param index The index of the element to return.
     * @return The categorias at the given index.
     */
    public java.lang.String getCategorias(int index) {
      return categorias_.get(index);
    }
    /**
     * <code>repeated string categorias = 3;</code>
     * @param index The index of the value to return.
     * @return The bytes of the categorias at the given index.
     */
    public com.google.protobuf.ByteString
        getCategoriasBytes(int index) {
      return categorias_.getByteString(index);
    }
    /**
     * <code>repeated string categorias = 3;</code>
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
     * <code>repeated string categorias = 3;</code>
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
     * <code>repeated string categorias = 3;</code>
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
     * <code>repeated string categorias = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearCategorias() {
      categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string categorias = 3;</code>
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


    // @@protoc_insertion_point(builder_scope:transacao.eventos.TransacaoCriada)
  }

  // @@protoc_insertion_point(class_scope:transacao.eventos.TransacaoCriada)
  private static final br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada();
  }

  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TransacaoCriada>
      PARSER = new com.google.protobuf.AbstractParser<TransacaoCriada>() {
    @java.lang.Override
    public TransacaoCriada parsePartialFrom(
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

  public static com.google.protobuf.Parser<TransacaoCriada> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TransacaoCriada> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.TransacaoCriada getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

