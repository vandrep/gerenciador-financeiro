// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/eventos_transacao.proto

package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

/**
 * Protobuf type {@code transacao.eventos.ItemAdicionado}
 */
public final class ItemAdicionado extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:transacao.eventos.ItemAdicionado)
    ItemAdicionadoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ItemAdicionado.newBuilder() to construct.
  private ItemAdicionado(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ItemAdicionado() {
    descricao_ = "";
    categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ItemAdicionado();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemAdicionado_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemAdicionado_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado.class, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado.Builder.class);
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

  public static final int VALOR_ITEM_FIELD_NUMBER = 2;
  private br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem valorItem_;
  /**
   * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
   * @return Whether the valorItem field is set.
   */
  @java.lang.Override
  public boolean hasValorItem() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
   * @return The valorItem.
   */
  @java.lang.Override
  public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem getValorItem() {
    return valorItem_ == null ? br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.getDefaultInstance() : valorItem_;
  }
  /**
   * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
   */
  @java.lang.Override
  public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItemOrBuilder getValorItemOrBuilder() {
    return valorItem_ == null ? br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.getDefaultInstance() : valorItem_;
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
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, descricao_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeMessage(2, getValorItem());
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
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, descricao_);
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getValorItem());
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
    if (!(obj instanceof br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado)) {
      return super.equals(obj);
    }
    br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado other = (br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado) obj;

    if (hasDescricao() != other.hasDescricao()) return false;
    if (hasDescricao()) {
      if (!getDescricao()
          .equals(other.getDescricao())) return false;
    }
    if (hasValorItem() != other.hasValorItem()) return false;
    if (hasValorItem()) {
      if (!getValorItem()
          .equals(other.getValorItem())) return false;
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
    if (hasDescricao()) {
      hash = (37 * hash) + DESCRICAO_FIELD_NUMBER;
      hash = (53 * hash) + getDescricao().hashCode();
    }
    if (hasValorItem()) {
      hash = (37 * hash) + VALOR_ITEM_FIELD_NUMBER;
      hash = (53 * hash) + getValorItem().hashCode();
    }
    if (getCategoriasCount() > 0) {
      hash = (37 * hash) + CATEGORIAS_FIELD_NUMBER;
      hash = (53 * hash) + getCategoriasList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado parseFrom(
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
  public static Builder newBuilder(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado prototype) {
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
   * Protobuf type {@code transacao.eventos.ItemAdicionado}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:transacao.eventos.ItemAdicionado)
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionadoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemAdicionado_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemAdicionado_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado.class, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado.Builder.class);
    }

    // Construct using br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getValorItemFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      descricao_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      if (valorItemBuilder_ == null) {
        valorItem_ = null;
      } else {
        valorItemBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      categorias_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000004);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.EventosTransacao.internal_static_transacao_eventos_ItemAdicionado_descriptor;
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado getDefaultInstanceForType() {
      return br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado.getDefaultInstance();
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado build() {
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado buildPartial() {
      br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado result = new br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        to_bitField0_ |= 0x00000001;
      }
      result.descricao_ = descricao_;
      if (((from_bitField0_ & 0x00000002) != 0)) {
        if (valorItemBuilder_ == null) {
          result.valorItem_ = valorItem_;
        } else {
          result.valorItem_ = valorItemBuilder_.build();
        }
        to_bitField0_ |= 0x00000002;
      }
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
      if (other instanceof br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado) {
        return mergeFrom((br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado other) {
      if (other == br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado.getDefaultInstance()) return this;
      if (other.hasDescricao()) {
        bitField0_ |= 0x00000001;
        descricao_ = other.descricao_;
        onChanged();
      }
      if (other.hasValorItem()) {
        mergeValorItem(other.getValorItem());
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
              descricao_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              input.readMessage(
                  getValorItemFieldBuilder().getBuilder(),
                  extensionRegistry);
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

    private br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem valorItem_;
    private com.google.protobuf.SingleFieldBuilderV3<
        br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.Builder, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItemOrBuilder> valorItemBuilder_;
    /**
     * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
     * @return Whether the valorItem field is set.
     */
    public boolean hasValorItem() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
     * @return The valorItem.
     */
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem getValorItem() {
      if (valorItemBuilder_ == null) {
        return valorItem_ == null ? br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.getDefaultInstance() : valorItem_;
      } else {
        return valorItemBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
     */
    public Builder setValorItem(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem value) {
      if (valorItemBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        valorItem_ = value;
        onChanged();
      } else {
        valorItemBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
     */
    public Builder setValorItem(
        br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.Builder builderForValue) {
      if (valorItemBuilder_ == null) {
        valorItem_ = builderForValue.build();
        onChanged();
      } else {
        valorItemBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
     */
    public Builder mergeValorItem(br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem value) {
      if (valorItemBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
            valorItem_ != null &&
            valorItem_ != br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.getDefaultInstance()) {
          valorItem_ =
            br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.newBuilder(valorItem_).mergeFrom(value).buildPartial();
        } else {
          valorItem_ = value;
        }
        onChanged();
      } else {
        valorItemBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
     */
    public Builder clearValorItem() {
      if (valorItemBuilder_ == null) {
        valorItem_ = null;
        onChanged();
      } else {
        valorItemBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
     */
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.Builder getValorItemBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getValorItemFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
     */
    public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItemOrBuilder getValorItemOrBuilder() {
      if (valorItemBuilder_ != null) {
        return valorItemBuilder_.getMessageOrBuilder();
      } else {
        return valorItem_ == null ?
            br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.getDefaultInstance() : valorItem_;
      }
    }
    /**
     * <code>optional .transacao.eventos.ObjetosDeValor.ValorItem valor_item = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.Builder, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItemOrBuilder> 
        getValorItemFieldBuilder() {
      if (valorItemBuilder_ == null) {
        valorItemBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItem.Builder, br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ObjetosDeValor.ValorItemOrBuilder>(
                getValorItem(),
                getParentForChildren(),
                isClean());
        valorItem_ = null;
      }
      return valorItemBuilder_;
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


    // @@protoc_insertion_point(builder_scope:transacao.eventos.ItemAdicionado)
  }

  // @@protoc_insertion_point(class_scope:transacao.eventos.ItemAdicionado)
  private static final br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado();
  }

  public static br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ItemAdicionado>
      PARSER = new com.google.protobuf.AbstractParser<ItemAdicionado>() {
    @java.lang.Override
    public ItemAdicionado parsePartialFrom(
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

  public static com.google.protobuf.Parser<ItemAdicionado> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ItemAdicionado> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public br.com.pine.gerenciador.modelo.dominio.transacao.eventos.ItemAdicionado getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

