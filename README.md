Hello Play
==========
Using the Scala/Play framework, implement an HTTP REST API implementing a product listing app.

Feature Set:
- Add Product
- Edit Product
- List Product (user can pass offset and limit — need to return total product count as well)
- Delete Product

To abstract the persistence layer, please use the Repository pattern. It is perfectly fine to use an in-memory persistence implementation.
Assume that the repository methods are async; it is required to use the Future abstraction in the Repository methods.

Other than this, you are free to come up with your own implementation decisions, including defining the product model schema.

Bonus question: Unit testing of the repository object.
