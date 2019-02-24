package com.flightmode.app.service

class MockAirportScheduleSuccessResponse {

    companion object {
        const val DATA = "[\n" +
                "  {\n" +
                "    \"type\": \"departure\",\n" +
                "    \"status\": \"scheduled\",\n" +
                "    \"departure\": {\n" +
                "      \"iataCode\": \"JFK\",\n" +
                "      \"icaoCode\": \"KJFK\",\n" +
                "      \"terminal\": \"5\",\n" +
                "      \"scheduledTime\": \"2019-02-24T12:00:00.000\"\n" +
                "    },\n" +
                "    \"arrival\": {\n" +
                "      \"iataCode\": \"ACK\",\n" +
                "      \"icaoCode\": \"KACK\",\n" +
                "      \"scheduledTime\": \"2019-02-24T13:42:00.000\"\n" +
                "    },\n" +
                "    \"airline\": {\n" +
                "      \"name\": \"JetBlue Airways\",\n" +
                "      \"iataCode\": \"B6\",\n" +
                "      \"icaoCode\": \"JBU\"\n" +
                "    },\n" +
                "    \"flight\": {\n" +
                "      \"number\": \"5921\",\n" +
                "      \"iataNumber\": \"B65921\",\n" +
                "      \"icaoNumber\": \"JBU5921\"\n" +
                "    },\n" +
                "    \"codeshared\": {\n" +
                "      \"airline\": {\n" +
                "        \"name\": \"Cape Air\",\n" +
                "        \"iataCode\": \"9K\",\n" +
                "        \"icaoCode\": \"KAP\"\n" +
                "      },\n" +
                "      \"flight\": {\n" +
                "        \"number\": \"101\",\n" +
                "        \"iataNumber\": \"9K101\",\n" +
                "        \"icaoNumber\": \"KAP101\"\n" +
                "      }\n" +
                "    }\n" +
                "  }" +
        "]"

    }
}