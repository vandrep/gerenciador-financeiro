// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: proto/eventos_transacao.proto

package br.com.pine.gerenciador.modelo.dominio.transacao.eventos;

public interface TransacaoCriadaOrBuilder extends
    // @@protoc_insertion_point(interface_extends:transacao.eventos.TransacaoCriada)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string id = 1;</code>
   * @return Whether the id field is set.
   */
  boolean hasId();
  /**
   * <code>optional string id = 1;</code>
   * @return The id.
   */
  java.lang.String getId();
  /**
   * <code>optional string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>optional string pagamento = 2;</code>
   * @return Whether the pagamento field is set.
   */
  boolean hasPagamento();
  /**
   * <code>optional string pagamento = 2;</code>
   * @return The pagamento.
   */
  java.lang.String getPagamento();
  /**
   * <code>optional string pagamento = 2;</code>
   * @return The bytes for pagamento.
   */
  com.google.protobuf.ByteString
      getPagamentoBytes();

  /**
   * <code>repeated string categorias = 3;</code>
   * @return A list containing the categorias.
   */
  java.util.List<java.lang.String>
      getCategoriasList();
  /**
   * <code>repeated string categorias = 3;</code>
   * @return The count of categorias.
   */
  int getCategoriasCount();
  /**
   * <code>repeated string categorias = 3;</code>
   * @param index The index of the element to return.
   * @return The categorias at the given index.
   */
  java.lang.String getCategorias(int index);
  /**
   * <code>repeated string categorias = 3;</code>
   * @param index The index of the value to return.
   * @return The bytes of the categorias at the given index.
   */
  com.google.protobuf.ByteString
      getCategoriasBytes(int index);
}
