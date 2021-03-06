package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 *
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();

		// print headers
		String header="======Printing Orders======\n";
		output.append(header);

		// print date, bill no, customer name
//        output.append("Date - " + order.getDate();
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
//        output.append(order.getCustomerLoyaltyNumber());

		// prints lineItems
		return printLineItem(output);
	}

	private String printLineItem(StringBuilder output) {
		double totalSaleTax = 0d;
		double totalPrice = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			append(output, lineItem);

			// calculate sales tax @ rate of 10%
			double taxRate=.10;
            double salesTax = lineItem.totalAmount() * taxRate;
            totalSaleTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            totalPrice += lineItem.totalAmount() + salesTax;
		}

		// prints the state tax
		output.append("Sales Tax").append('\t').append(totalSaleTax);

		// print total amount
		output.append("Total Amount").append('\t').append(totalPrice);
		return output.toString();
	}

	private void append(StringBuilder output, LineItem lineItem) {
		output.append(lineItem.getDescription());
		output.append('\t');
		output.append(lineItem.getPrice());
		output.append('\t');
		output.append(lineItem.getQuantity());
		output.append('\t');
		output.append(lineItem.totalAmount());
		output.append('\n');
	}
}
