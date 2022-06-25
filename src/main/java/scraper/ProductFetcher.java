package scraper;

import models.Product;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ProductFetcher {
    public static String getProductCollectionFromUrl(String url) {
        String[] tokens = url.split("/");

        if (tokens.length > 3) {
            return tokens[tokens.length-3];
        }
        else {
            return "";
        }
    }

    private static String getProductName(Element parentTag) {
        Element head1 = parentTag.select("h1").first();
        if (head1 == null) {
            return "Unknown";
        }
        return head1.text();
    }

    private static String getProductCode(Element parentTag) {
        Element productPriceTag = parentTag.getElementById("product-price");
        if (productPriceTag == null) {
            return "";
        }
        Element spanVariantTag = productPriceTag.select("span.variant-sku").first();
        return spanVariantTag.text();
    }

    private static float getProductPrice(Element parentTag) {
        Element spanMoneyTag = parentTag.select("span.money").first();
        if (spanMoneyTag == null) {
            return 0;
        }
        String priceString = spanMoneyTag.text();
        String[] priceWords = priceString.split("\\s+");
//        String currency = priceWords[priceWords.length-1];
        return Float.parseFloat(priceWords[0].replace(",", ""));
    }

    private static String getProductHighlight(Element parentTag) {
        Elements elements = parentTag.select("h2 ~ *");
        if (elements.isEmpty()) {
            return "";
        }
        StringBuilder highlightBuilder = new StringBuilder();
        for (Element element : elements) {
            String tagName = element.tag().getName();
            if (tagName.equals("ul") || tagName.equals("ol")) {
                Elements listItems = element.getElementsByTag("li");
                for (Element item : listItems) {
                    highlightBuilder.append(item.text());
                    highlightBuilder.append("\n");
                }
            } else {
                highlightBuilder.append(element.text());
                highlightBuilder.append("\n");
            }
        }
        return highlightBuilder.toString();
    }

    public static String getProductImageUrl(Element parentTag) {
        Element productImageTag = parentTag.getElementById("product-photos");
        if (productImageTag == null) {
            return "";
        }
        Element imageTag = productImageTag.select("div.bigimage > img").first();
        if (imageTag == null) {
            return "";
        }
        return imageTag.attr("src");
    }
    public static Product fetchProductData(String url) {
        Document doc =  DocumentFetcher.getDocumentFromUrl(url);
        if (doc == null) {
            return null;
        }
        Element descriptionTag = doc.getElementById("product-description");
        String productName = "";
        String highlight = "";
        float price = 0;

        if (descriptionTag != null) {
            // get product name
            productName = ProductFetcher.getProductName(descriptionTag);
            // fetch product highlights
            highlight = ProductFetcher.getProductHighlight(descriptionTag);
            // get product price
            price = ProductFetcher.getProductPrice(descriptionTag);
        }

        // get product code
        String productCode = ProductFetcher.getProductCode(doc);
        // fetch product image
        String productImageUrl = ProductFetcher.getProductImageUrl(doc);
        // fetch product collection
        String productCollection = ProductFetcher.getProductCollectionFromUrl(url);

        // create product
        Product product = new Product(url, productName, productCode, price);
        
        product.setCollection(productCollection);
        product.setHighlight(highlight);
        product.setImageUrl(productImageUrl);

        return product;
    }
}
