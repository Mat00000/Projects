# Lista nr 4
1. **Zadanie** (5pt) Napisz aplikacje (galeria) przechowującą zdjęcia np. ludzi, krajobrazów, zwierząt, ... i każde zdjęcie dodatkowo zawiera krótki opis. Po uruchomieniu aplikacji, na początku pokazuje ona dostępne zdjęcia. Użytkownik może wybrać dowolną pozycję, aby zobaczyć większe zdjęcie i opis. Na ekranie dodatkowo, mamy możliwość ocenienia zdjęcia przez np. "gwiazdki" (zobacz **RatingBar** ). Proszę pamiętać, że na tym etapie poznania Androida nie ma to być w pełni funkcjonalna aplikacja np. nie potrzeba tworzyć kont dla użytkowników lub nie potrzeba przechowywać nowych zdjęć. Aplikacja powinna natomiast obsługiwać:
    + co najmniej dwie aktywności
    + przekazywać informacje z jednej aktywności do drugiej wykorzystując intencje
    + druga aktywność powinna wracać informacje do pierwszej o liczbie gwiazdek, po czym w pierwszej aktywności obrazki zostają odpowiednio posortowane po liczbie gwiazdek
    + poprawie obsługiwać cykl życia aktywności tzn. onCreate, onStart, onResume, onPause, onStop, onDestroy, ... (te które są potrzebne)
    + wykorzystywać fragmenty przy zmianie orientacji ekranu
    + zapamiętywać swój stan po zmianie orientacji ekranu
2. **Zadanie** (5pt) Uzupełnij poprzednie zadanie o możliwość robienia zdjęć (obsługa **Camera** ) i dodawania do kolekcji.