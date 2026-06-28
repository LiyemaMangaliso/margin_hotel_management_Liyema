package za.ac.cput.marginhotelmanagement.factory;
/*
    Author: MS Malapile (222904267)
    Date: 27 June 2026
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import za.ac.cput.marginhotelmanagement.domain.Booking;
import za.ac.cput.marginhotelmanagement.domain.Invoice;
import za.ac.cput.marginhotelmanagement.enums.InvoiceStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
class InvoiceFactoryTest {

    private Booking booking;

    @BeforeEach
    void setUp() {
        this.booking = new Booking();
    }

    @Test
    @Order(1)
    void createInvoiceSuccess() {
        Invoice invoice = InvoiceFactory.createInvoice(
                1L,
                "INV-001",
                1500.00,
                InvoiceStatus.PENDING,
                LocalDate.now().minusDays(1),
                booking
        );
        assertNotNull(invoice);
        assertEquals(1L, invoice.getInvoiceId());
        assertEquals("INV-001", invoice.getReference());
        assertEquals(1500.00, invoice.getTotalAmount());
        assertEquals(InvoiceStatus.PENDING, invoice.getStatus());
        System.out.println("Invoice created successfully: " + invoice);
    }

    @Test
    @Order(2)
    void createInvoiceWithInvalidId() {
        Invoice invoice = InvoiceFactory.createInvoice(
                -1L,
                "INV-002",
                1500.00,
                InvoiceStatus.PENDING,
                LocalDate.now().minusDays(1),
                booking
        );
        assertNull(invoice);
        System.out.println("Invoice creation failed due to invalid invoice ID.");
    }

    @Test
    @Order(3)
    void createInvoiceWithInvalidTotalAmount() {
        Invoice invoice = InvoiceFactory.createInvoice(
                2L,
                "INV-003",
                0,
                InvoiceStatus.PENDING,
                LocalDate.now().minusDays(1),
                booking
        );
        assertNull(invoice);
        System.out.println("Invoice creation failed due to invalid total amount.");
    }

    @Test
    @Order(4)
    void createInvoiceWithNullReference() {
        Invoice invoice = InvoiceFactory.createInvoice(
                3L,
                null,
                1500.00,
                InvoiceStatus.PENDING,
                LocalDate.now().minusDays(1),
                booking
        );
        assertNull(invoice);
        System.out.println("Invoice creation failed due to null reference.");
    }

    @Test
    @Order(5)
    void createInvoiceWithFutureIssueDate() {
        Invoice invoice = InvoiceFactory.createInvoice(
                4L,
                "INV-005",
                1500.00,
                InvoiceStatus.PENDING,
                LocalDate.now().plusDays(1),
                booking
        );
        assertNull(invoice);
        System.out.println("Invoice creation failed due to future issue date.");
    }
}