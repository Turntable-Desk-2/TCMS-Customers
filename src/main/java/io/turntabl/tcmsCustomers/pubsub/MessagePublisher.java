package io.turntabl.tcmsCustomers.pubsub;

import io.turntabl.tcmsCustomers.models.CustomerTO;

public interface MessagePublisher {
    void publish(CustomerTO customer);
}
