package shop.tantangan;

import java.util.List;
import shop.model.Address;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.notify.NotificationChannel;
import shop.notify.SmsChannel;
import shop.notify.WhatsAppChannel;
import shop.service.Notifier;

public class TantanganB {
    public static void main(String[] args) {
        Order order = new Order("DEMO-B1", new Address("Wati", "Bandung"))
                .add(new PhysicalProduct("KOPI", "Kopi Gayo 250g", 85_000, 300), 1);

        List<NotificationChannel> channels = List.of(new SmsChannel(), new WhatsAppChannel());
        new Notifier(channels).notifyPaid(order, 85_000);
    }
}