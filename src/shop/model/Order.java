package shop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String orderId;
    private final Address shippingAddress;
    private final List<Line> lines = new ArrayList<>();

    public Order(String orderId, Address shippingAddress) {
        if (orderId == null || orderId.isBlank() || shippingAddress == null) {
            throw new IllegalArgumentException("Order id and address are required");
        }
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
    }

    public String getOrderId() { return orderId; }
    public Address getShippingAddress() { return shippingAddress; }

    public void addLine(Product product, int quantity) {
        lines.add(new Line(product, quantity));
    }

    public List<Line> getLines() { return Collections.unmodifiableList(lines); }

    public long getItemsTotal() {
        long total = 0;
        for (Line line : lines) {
            total += line.subtotal();
        }
        return total;
    }

    public Receipt createReceipt() {
        return new Receipt();
    }

<<<<<<< HEAD
    // L5.b.1: Static nested class Line
=======
    /** One line of an order. Static: it does not need the Order object. */
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
    public static class Line {
        private final Product product;
        private final int quantity;

        public Line(Product product, int quantity) {
            if (product == null || quantity <= 0) {
<<<<<<< HEAD
                throw new IllegalArgumentException("Invalid product or quantity");
=======
                throw new IllegalArgumentException("Product is required and quantity must be positive");
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
            }
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() { return product; }
        public int getQuantity() { return quantity; }

        public long subtotal() {
            return product.getFinalPrice() * quantity;
        }
    }

<<<<<<< HEAD
    // L5.b.3: Inner class Receipt (non-static) yang membaca data Order luar via Order.this
    public class Receipt {
        public String toText() {
            StringBuilder sb = new StringBuilder();
            sb.append("STRUK PESANAN ").append(Order.this.orderId).append("\n");
            for (Line line : lines) {
                sb.append("- ").append(line.getProduct().getName())
                  .append(" x").append(line.getQuantity())
                  .append(" : Rp").append(line.subtotal()).append("\n");
            }
            sb.append("Total barang: Rp").append(getItemsTotal()).append("\n");
            sb.append("Kirim ke: ").append(Order.this.shippingAddress);
            return sb.toString();
        }
    }
}
=======
    /** Receipt of one specific order. Inner: it reads the outer Order. */
    public class Receipt {
        public String toText() {
            StringBuilder text = new StringBuilder();
            text.append("STRUK PESANAN ").append(Order.this.orderId).append('\n');
            for (Line line : lines) {
                text.append("- ").append(line.getProduct().getName())
                    .append(" x").append(line.getQuantity())
                    .append(" = Rp").append(line.subtotal()).append('\n');
            }
            text.append("Total barang: Rp").append(getItemsTotal()).append('\n');
            text.append("Kirim ke: ").append(Order.this.shippingAddress);
            return text.toString();
        }
    }
}
>>>>>>> 44d8345 (Praktikum P07 - Exception 2511533023 M.Fajar Fadhilul Zikri)
