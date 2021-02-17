package com.company.app.customerservice.api.sagaparticipants.commands;

import io.eventuate.tram.commands.common.Command;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.money.MonetaryAmount;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class ReserveCreditCommand implements Command {

    private final Integer orderId;

    private final MonetaryAmount totalPrice;

    private final Integer customerId;
}
