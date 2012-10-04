package com.cms.gwt.fs.client.model.item;



public class ServiceTicketItem {

    private String ticketNumber;
    private AssetInformation assetInfo;
    private ProductInformation productInfo;
        			
	
	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public AssetInformation getAssetInformation() {
		return assetInfo;
	}

	public void setAsset(AssetInformation asset) {
		this.assetInfo = asset;
	}

	public ProductInformation getProductInformation() {
		return productInfo;
	}

	public void setProductInformation(ProductInformation product) {
		this.productInfo = product;
	}

}
