# IKEA-Ukraine-Checker

Personal project which I made when I had to move to different place and buy staff from IKEA and was wondering all the time 
if the price for such product in Ukraine cheaper than in other countries or not. Another case was that in Ukrainian version of IKEA 
they don't have an product rating and when you are looking to buy something, customer ratings could have a great impact – you can easily 
buy something with 4.6 rating, but you'll check reviews more deep if it has 3.0. As IKEA doesn't have a public API, project realized 
with parsing HTML code, which was realized using Jsoup. I've used parallelStream() to make it faster. 

`INPUT`: IKEA URL (any country) or 8-digit product number.
`OUTPUT`: Information about price in local currency and converted to UAH as well as rating information. Result will be sorted. If product 
is not available in some countries – it will be showed in output. 

Simple OUTPUT: 
```
Paste link or ID: https://www.ikea.com/ua/uk/p/-20302596
49      грн in  Ukraine               (49.0 ₴)            
51      грн in  Canada                (2.44 C$)       ★★★★4.7 (33)
60      грн in  Czech Republic        (49.0 Kč)       ★★★★4.9 (51)
64      грн in  Slovakia              (1.99 €)        ★★★★4.9 (51)
64      грн in  Croatia               (14.9 kn)       ★★★★4.9 (24)
68      грн in  United States         (2.44 $)        ★★★★4.7 (33)
71      грн in  Great Britain         (2.0 £)         ★★★★4.7 (68)
79      грн in  Germany               (2.44 €)        ★★★★4.7 (33)
91      грн in  Sweden                (29.0 kr)           
```

Adding new countries is easy – you just need to create a new class which extends Country abstract class and define country name, currency and currency rate. 

IMPORTANT: as some items could have a bit different size (for example table which is 2cm wider than in Europe) – they have different product numbers and there are
no chance to link them. 
