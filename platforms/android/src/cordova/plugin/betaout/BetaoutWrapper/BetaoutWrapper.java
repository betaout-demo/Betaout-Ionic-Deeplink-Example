package cordova.plugin.betaout;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.apache.cordova.CordovaInterface; 
import org.apache.cordova.CordovaWebView; 

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.betaout.sdk.model.BOCart;
import com.betaout.sdk.model.BOOrder;
import com.betaout.sdk.model.BOProduct;

import com.betaout.sdk.app.BetaOutConfig;
import com.betaout.sdk.app.BetaOut;

import android.app.Application;

import android.content.BroadcastReceiver; 
import android.content.Context; 
import android.content.Intent; 
import android.content.IntentFilter;

import org.apache.cordova.PluginResult;
import org.apache.cordova.LOG;
import android.net.Uri;


/**
 * This class echoes a string called from JavaScript.
 */
public class BetaoutWrapper extends CordovaPlugin {
	

	private CallbackContext deeplinkCallbackContext = null;
	public static Intent startIntent;
	
	@Override 
     public void initialize(CordovaInterface cordova, CordovaWebView webView) { 
         super.initialize(cordova, webView); 
		 BetaoutWrapper.startIntent = cordova.getActivity().getIntent();
     } 


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        /*if (action.equals("init")) {
			System.out.println("Called");
			Application context = this.cordova.getActivity().getApplication();
            String apiKey = args.getString(0);
            String projectId = args.getString(1);
            String senderId = args.getString(2);
            boolean isFCM = args.getBoolean(3);
		
			BetaOutConfig config = BetaOutConfig.init(apiKey, projectId, senderId, isFCM);
			BetaOut.init(config, context);
			callbackContext.success("Initialized");
            return true;
        }else{
			callbackContext.error("Please check Function's name");
			return false;
		}*/
		if(action.equals("startDeeplinkReceiver")){
			if (this.deeplinkCallbackContext != null) { 
                callbackContext.error( "Deeplink listener already running."); 
                return true; 
            } 
            this.deeplinkCallbackContext = callbackContext; 
			JSONObject data = getData(BetaoutWrapper.startIntent);
			if(data != null){
				sendUpdate(data, true);
			}
			
            return true; 

		}
		

		if(action.equals("setCustomerId")){
			BetaOut.getInstance().setCustomerId(args.getString(0));
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("setCustomerEmail")){
			BetaOut.getInstance().setCustomerEmail(args.getString(0));
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("setCustomerPhone")){
			BetaOut.getInstance().setCustomerPhone(args.getString(0));
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("viewProduct")){
			BOProduct product = BOProduct.create(args.getString(0), Float.parseFloat(args.getString(1)));// Product ID, Product price
			try{
				JSONArray jsonArray = args.getJSONArray(8);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					product.addCategoryToProduct(jsonObject.getString("categoryId"), jsonObject.getString("categoryName"), jsonObject.getString("parentCategoryId"));//CategoryID, Category Name, Parent CategoryID
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			product.setBrand(args.getString(2));
			product.setName(args.getString(3));
			product.setProductImageUrl(args.getString(4));
			product.setProductUrl(args.getString(5));
			product.setQuantity(args.getInt(6));
			product.setSku(args.getString(7));
			BetaOut.getInstance().viewProducts(product);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("addToCart")){
			BOCart mBOCart = BOCart.create(Float.parseFloat(args.getString(0)), Float.parseFloat(args.getString(1)), args.getString(2));// Cart Total Value, Revenue from Cart,
			//Currency of Purchase in String
			mBOCart.setAbandon_cart_url(args.getString(3));
			mBOCart.setAbandon_cart_deeplink_ios(args.getString(4));
			mBOCart.setAbandon_cart_deeplink_android(args.getString(5));
			
			BOProduct product = BOProduct.create(args.getString(6), Float.parseFloat(args.getString(7)));// Product ID, Product price
			try{
				JSONArray jsonArray = args.getJSONArray(14);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					product.addCategoryToProduct(jsonObject.getString("categoryId"), jsonObject.getString("categoryName"), jsonObject.getString("parentCategoryId"));//CategoryID, Category Name, Parent CategoryID
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			product.setBrand(args.getString(8));
			product.setName(args.getString(9));
			product.setProductImageUrl(args.getString(10));
			product.setProductUrl(args.getString(11));
			product.setQuantity(args.getInt(12));
			product.setSku(args.getString(13));
			BetaOut.getInstance().addProductsToCart(product, mBOCart);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("removeFromCart")){
			BOCart mBOCart = BOCart.create(Float.parseFloat(args.getString(0)), Float.parseFloat(args.getString(1)), args.getString(2));// Cart Total Value, Revenue from Cart,
			//Currency of Purchase in String
			mBOCart.setAbandon_cart_url(args.getString(3));
			mBOCart.setAbandon_cart_deeplink_ios(args.getString(4));
			mBOCart.setAbandon_cart_deeplink_android(args.getString(5));
			
			BOProduct product = BOProduct.create(args.getString(6), Float.parseFloat(args.getString(7)));// Product ID, Product price
			try{
				JSONArray jsonArray = args.getJSONArray(14);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					product.addCategoryToProduct(jsonObject.getString("categoryId"), jsonObject.getString("categoryName"), jsonObject.getString("parentCategoryId"));//CategoryID, Category Name, Parent CategoryID
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			product.setBrand(args.getString(8));
			product.setName(args.getString(9));
			product.setProductImageUrl(args.getString(10));
			product.setProductUrl(args.getString(11));
			product.setQuantity(args.getInt(12));
			product.setSku(args.getString(13));
			BetaOut.getInstance().removeProductsForCart(product, mBOCart);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("updateCart")){
			BOCart mBOCart = BOCart.create(Float.parseFloat(args.getString(0)), Float.parseFloat(args.getString(1)), args.getString(2));// Cart Total Value, Revenue from Cart,
			//Currency of Purchase in String
			mBOCart.setAbandon_cart_url(args.getString(3));
			mBOCart.setAbandon_cart_deeplink_ios(args.getString(4));
			mBOCart.setAbandon_cart_deeplink_android(args.getString(5));
			
			BOProduct product = BOProduct.create(args.getString(6), Float.parseFloat(args.getString(7)));// Product ID, Product price
			try{
				JSONArray jsonArray = args.getJSONArray(14);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					product.addCategoryToProduct(jsonObject.getString("categoryId"), jsonObject.getString("categoryName"), jsonObject.getString("parentCategoryId"));//CategoryID, Category Name, Parent CategoryID
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			product.setBrand(args.getString(8));
			product.setName(args.getString(9));
			product.setProductImageUrl(args.getString(10));
			product.setProductUrl(args.getString(11));
			product.setQuantity(args.getInt(12));
			product.setSku(args.getString(13));
			BetaOut.getInstance().updateProductsForCart(product, mBOCart);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("readyToPurchase")){
			BOCart mBOCart = BOCart.create(Float.parseFloat(args.getString(0)), Float.parseFloat(args.getString(1)), args.getString(2));// Cart Total Value, //Revenue from Cart,
			//Currency of Purchase in String
			mBOCart.setAbandon_cart_url(args.getString(3));
			mBOCart.setAbandon_cart_deeplink_ios(args.getString(4));
			mBOCart.setAbandon_cart_deeplink_android(args.getString(5));
			
			
			List<BOProduct> products = new ArrayList<BOProduct>();
			try{
				JSONArray jsonArray = args.getJSONArray(6);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					BOProduct product = BOProduct.create(jsonObject.getString("productId"), Float.parseFloat(jsonObject.getString("productPrice")));// Product ID, Product price
					try{
						JSONArray jsonArray2 = jsonObject.getJSONArray("productCategories");
						for(int j = 0; j < jsonArray2.length(); j++){
							JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
							product.addCategoryToProduct(jsonObject2.getString("categoryId"), jsonObject2.getString("categoryName"), jsonObject2.getString("parentCategoryId"));//CategoryID, Category Name, Parent CategoryID
						}
					}catch(JSONException e){
						e.printStackTrace();
					}
					product.setBrand(jsonObject.getString("productBrand"));
					product.setName(jsonObject.getString("productName"));
					product.setProductImageUrl(jsonObject.getString("productImageUrl"));
					product.setProductUrl(jsonObject.getString("productUrl"));
					product.setQuantity(jsonObject.getInt("productQuantity"));
					product.setSku(jsonObject.getString("productSKU"));
					products.add(product);
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			BOOrder mBOOrder = BOOrder.create(args.getString(7), Float.parseFloat(args.getString(8)), Float.parseFloat(args.getString(9)), args.getString(10), args.getString(11), args.getString(12));// Order
			//ID, Order Total Revenue from Cart, Currency of Purchase in String, Status, Method of Payment
			mBOOrder.setCoupon(args.getString(13));// Any coupon or promo code applied
			mBOOrder.setShipping(Float.parseFloat(args.getString(14)));// Any shipping charges
			mBOOrder.setTax(Float.parseFloat(args.getString(15)));// Any tax applicable
			mBOOrder.setDiscount(Float.parseFloat(args.getString(16)));// Any discount applicable
			mBOOrder.setShipping_method(args.getString(17));// Method of Shipping
			
			Hashtable<String, String> mList = new Hashtable<String, String>();
			try{
				JSONArray jsonArray = args.getJSONArray(17);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					mList.put(jsonObject.getString("name"), jsonObject.getString("value"));
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			BetaOut.getInstance().processingCartForPurchaseWithOrder(mBOOrder, mBOCart, products, mList);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("completePurchase")){
			BOCart mBOCart = BOCart.create(Float.parseFloat(args.getString(0)), Float.parseFloat(args.getString(1)), args.getString(2));// Cart Total Value, //Revenue from Cart,
			//Currency of Purchase in String
			mBOCart.setAbandon_cart_url(args.getString(3));
			mBOCart.setAbandon_cart_deeplink_ios(args.getString(4));
			mBOCart.setAbandon_cart_deeplink_android(args.getString(5));
			BOOrder mBOOrder = BOOrder.create(args.getString(6), Float.parseFloat(args.getString(7)), Float.parseFloat(args.getString(8)), args.getString(9), args.getString(10), args.getString(11));// Order
			//ID, Order Total Revenue from Cart, Currency of Purchase in String, Status, Method of Payment
			mBOOrder.setCoupon(args.getString(12));// Any coupon or promo code applied
			mBOOrder.setShipping(Float.parseFloat(args.getString(13)));// Any shipping charges
			mBOOrder.setTax(Float.parseFloat(args.getString(14)));// Any tax applicable
			mBOOrder.setDiscount(Float.parseFloat(args.getString(15)));// Any discount applicable
			mBOOrder.setShipping_method(args.getString(16));// Method of Shipping
			mBOOrder.updateOrderStatus("Complete");
			
			Hashtable<String, String> mList = new Hashtable<String, String>();
			try{
				JSONArray jsonArray = args.getJSONArray(17);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					mList.put(jsonObject.getString("name"), jsonObject.getString("value"));
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			BetaOut.getInstance().completePurchaseOrder(mBOOrder, mList);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("clearCart")){
			BOCart mBOCart = BOCart.create(Float.parseFloat(args.getString(0)), Float.parseFloat(args.getString(7)), args.getString(2));// Cart Total Value, //Revenue from Cart,
			//Currency of Purchase in String
			mBOCart.setAbandon_cart_url(args.getString(3));
			mBOCart.setAbandon_cart_deeplink_ios(args.getString(4));
			mBOCart.setAbandon_cart_deeplink_android(args.getString(5));
			BetaOut.getInstance().clearCart(mBOCart);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("logEvent")){
			BetaOut.getInstance().logEvents(args.getString(0));
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("logEvents")){
			List<String> mList = new ArrayList<String>();
			try{
				JSONArray jsonArray = args.getJSONArray(0);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					mList.add(jsonObject.getString("event"));
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			BetaOut.getInstance().logEvents(mList);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("logout")){
			BetaOut.getInstance().logout();
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("updateUserProperties")){
			Hashtable<String, String> userProperties = new Hashtable<String, String>();
			try{
				JSONArray jsonArray = args.getJSONArray(0);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					userProperties.put(jsonObject.getString("name"), jsonObject.getString("value"));
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			BetaOut.getInstance().updateUserProperties(userProperties);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("incrementUserProperties")){
			Hashtable<String, String> userProperties = new Hashtable<String, String>();
			try{
				JSONArray jsonArray = args.getJSONArray(0);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					userProperties.put(jsonObject.getString("name"), jsonObject.getString("value"));
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			BetaOut.getInstance().incrementUserProperties(userProperties);
			callbackContext.success("Success");
			return true;
		}
		if(action.equals("appendUserProperties")){
			Hashtable<String, String> userProperties = new Hashtable<String, String>();
			try{
				JSONArray jsonArray = args.getJSONArray(0);
				for(int i = 0; i < jsonArray.length(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					userProperties.put(jsonObject.getString("name"), jsonObject.getString("value"));
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			BetaOut.getInstance().appendUserProperties(userProperties);
			callbackContext.success("Success");
			return true;
		}
		
		callbackContext.error("Function not found");
		return false;
		
    }
	
	private JSONObject getData(Intent intent){
		JSONObject obj = new JSONObject();
		try{
			Uri uri = intent.getData();
			if(uri != null){
				obj.put("deeplink", uri.toString());
				return obj;
			}
		}catch(Exception e){
			LOG.e("Betaout", e.getMessage(), e);
		}
		return null;
	}
	
	public void onNewIntent(Intent intent){
		sendUpdate(getData(intent), true);
	}
	
		
	private void sendUpdate(JSONObject info, boolean keepCallback) { 
        if (this.deeplinkCallbackContext != null) { 
            PluginResult result = new PluginResult(PluginResult.Status.OK, info); 
            result.setKeepCallback(keepCallback); 
            this.deeplinkCallbackContext.sendPluginResult(result); 
        } 
    } 

}
