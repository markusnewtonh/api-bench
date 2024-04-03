
class ProductData:
    
    # Small message product info
    @staticmethod
    def get_small_product_name():
        return "Pasta"
    
    @staticmethod
    def get_small_product_description():
        return "Boil for 10 min."
    
    # Typical message product info
    @staticmethod
    def get_typical_product_name():
        return "Läsk 7-UP"
    
    @staticmethod
    def get_typical_product_description():
        return "7UP är en läskedryck med en frisk och fräsch smak av lemon/lime. \
                7UP är en del av Pepsifamiljen och ett stort internationellt och \
                mycket välkänt varumärke. 7UP bör serveras väl kyld, gärna med is.\
                Passar såväl till mat och snacks som att avnjutas för sig själv. \
                Nu med ca 30% mindre socker."
    
    
    # LArge message product info
    @staticmethod
    def get_large_product_name():
        return "Large product"
    
    @staticmethod
    def get_large_product_description():
        # approx. x MB in size
        x = 1
        return "a" * x * 1_000_000
    