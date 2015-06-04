package domain
package object model {
  def uuid = java.util.UUID.randomUUID
  type ProductId = java.util.UUID
  type VariantId = java.util.UUID
}
