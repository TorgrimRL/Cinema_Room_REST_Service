# Cinema Room REST Service (Kotlin)

Cinema Room REST Service er et web-basert API-prosjekt utviklet i Kotlin med Spring Boot. Prosjektet håndterer billettkjøp, refusjon og visning av kinobilletter, og inkluderer validering av input, tilpasset feilhåndtering og endpoint-statistikk. Prosjektet er designet for å bestå Hyperskills interne tester, og demonstrerer en tydelig separasjon av ansvar mellom kontroller (som håndterer HTTP-forespørsler), forretningslogikk (domene- og serviceklasser) og datatilgang (lagret i en tråd-sikker struktur).
Designvalg

## Separation of Concerns:

  Controller: Håndterer alle HTTP-endepunkter for visning av setekart, kjøp og retur av billetter, samt statistikk.

  Domain: Inneholder forretningslogikken og modellene (som Ticket, Cinema og Purchase) som representerer kinoens data.

  Exception Handling: Spesialtilpassede feilmeldinger og unntak (SeatTakenException, WrongPasswordException, WrongTokenException) sørger for en robust API-respons.

## Validering og feilhåndtering:

  Inputvalidering er implementert med Jakarta/Javax Validation for å sikre korrekt dataformat.

  Tilpasset ExceptionHandler gir konsistente feilmeldinger til klientene.

## Tråd-sikkerhet og deling av data:

  Kinoens data håndteres via en ConcurrentHashMap for å sikre sikker tilgang når flere brukere interagerer med API-et samtidig.

## Komme i gang
### Kloning

For å klone prosjektet, kjør følgende kommando i terminalen:

    git clone https://github.com/yourusername/your-repository.git

### Navigere til riktig mappe

Etter nedlasting ligger prosjektet i en mappe med navnet Cinema Room REST Service (Kotlin). Alle Gradle-konfigurasjonsfiler og kildekode er plassert i undermappen. For å bygge og kjøre prosjektet, naviger inn i task-mappen:

    cd "Cinema Room REST Service (Kotlin)"

### Bygging

Prosjektet bygges med Gradle Wrapper og er konfigurert for å kjøre med Java 21. I mappen task, kjør:

    ./gradlew build

Dette kompilerer kildekoden, kjører tester og pakker applikasjonen.
### Kjøring

For å starte applikasjonen med Spring Boot, kjør:

    ./gradlew bootRun

Merk:
Når applikasjonen kjører, starter den Tomcat på port 28852. Siden standardruten på denne porten kanskje ikke viser noe, må du benytte et spesifikt endepunkt. For eksempel for å vise json av tilgjengelige seter:

    http://localhost:28852/seats   




# Noen av de tingene jeg lærte:

I prosjektet fikk jeg praktisert  hvordan man setter krav til innkommende data ved å bruke annotasjonene @Valid og @RequestBody, noe som blant annet ble utnyttet til å validere purchase requests. Jeg lærte også hvordan man kaster og håndterer exceptions, samt hvordan @ControllerAdvice og @ExceptionHandler kan samarbeide for å levere feilmeldinger. Det var lærerikt å override metoden handleMethodArgumentNotValid for å tilpasse feilhåndteringen ytterligere. I tillegg fikk jeg manipulert JSON-variabler med @get:JsonIgnore og @JsonProperty, samt å definert egne exception-klasser for spesifikke feil. Til slutt ble Cinema bevist implementert som et singleton-objekt for å forenkle tilgangen til kinoens data i hele applikasjonen.


