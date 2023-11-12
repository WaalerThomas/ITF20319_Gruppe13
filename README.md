# ITF20319_Gruppe13
"ITF20319-1 23H Software Engineering og testing" gruppeprosjekt. Skal lage en prototype av en applikasjon for å forenkle
prosessen for booking av omvisnigner/turer.

## Krav
- Java 17
- Maven ~3.9.1

## Kjøre kode / bygge
### IntelliJ IDEA
1. Åpne mappen **ITF20319_Gruppe13**, som inneholder pom.xml filen i IntelliJ IDEA.
2. I prosjektstrukturen, finn filen Main. *ITF20319_Gruppe13* -> *src* -> *main* -> *java* -> *no.booking* -> *Main*
3. Høyreklikk Main filen og velg **Run 'Main.main()'**

### Kommandovindu
1. Naviger til mappen **ITF20319_Gruppe13**, som inneholder pom.xml filen.
2. Kjør kommandoen `mvn compile`
3. Kjør kommandoen `mvn exec:java -Dexec.mainClass=no.booking.Main`

## Testing
### IntelliJ IDEA
1. I prosjektstrukturen, høyreklikk på **java** mappen under *ITF20319_Gruppe13* -> *src* -> *test* -> *java*
2. Velg **Run 'All Tests'**

### Kommandovindu
1. Naviger til mappen **ITF20319_Gruppe13**, som inneholder pom.xml filen.
2. Run `mvn test`