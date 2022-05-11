#!/bin/bash

echo "[REQ]"
echo "curl localhost:8080/api/screenings?startTime=2022-05-11T15:00&endTime=2022-06-30T21:00"
echo "[RES]"
curl "localhost:8080/api/screenings?startTime=2022-05-11T15:00&endTime=2022-06-30T21:00"
echo
echo

echo "[REQ]"
echo "curl localhost:8080/api/screenings/1"
echo "[RES]"
curl localhost:8080/api/screenings/1
echo
echo

echo "[REQ]"
echo "curl -H "Content-Type: application/json" -X POST -d '{
    "idScreening": 1,
    "idUser": 1,
    "name": "John",
    "surname": "Doe",
    "reservationTickets":[
        {
            "ticketType": "adult",
            "numberOfTicketType": 2
        },
        {
            "ticketType": "child",
            "numberOfTicketType": 3
        }
    ],
    "seats": [
        {
            "seatNumber": 0,
            "rowNumber": 0
        },
        {
            "seatNumber": 1,
            "rowNumber": 0
        },
        {
            "seatNumber": 2,
            "rowNumber": 0
        },
        {
            "seatNumber": 3,
            "rowNumber": 0
        },
        {
            "seatNumber": 4,
            "rowNumber": 0
        }
    ]
}' localhost:8080/api/reservations"
echo "[RES]"
curl -H "Content-Type: application/json" -X POST -d '{
    "idScreening": 1,
    "idUser": 1,
    "name": "John",
    "surname": "Doe",
    "reservationTickets":[
        {
            "ticketType": "adult",
            "numberOfTicketType": 2
        },
        {
            "ticketType": "child",
            "numberOfTicketType": 3
        }
    ],
    "seats": [
        {
            "seatNumber": 0,
            "rowNumber": 0
        },
        {
            "seatNumber": 1,
            "rowNumber": 0
        },
        {
            "seatNumber": 2,
            "rowNumber": 0
        },
        {
            "seatNumber": 3,
            "rowNumber": 0
        },
        {
            "seatNumber": 4,
            "rowNumber": 0
        }
    ]
}' localhost:8080/api/reservations
echo