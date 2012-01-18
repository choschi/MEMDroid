package com.choschi.memdroid.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.choschi.memdroid.R;
import com.choschi.memdroid.interfaces.AdapterItem;

/**
 * 
 * @author Christoph Isch
 * 
 * Fixed List contains lists that actually should be delivered by the web service for reasons of system integrity
 * as we need these values and everything is still beta I just put them in lists localised through the values directories 
 * 
 */

public class FixedLists {
	
	private static FixedLists instance = new FixedLists();
	
	private List<AdapterItem> languages;
	private List<AdapterItem> countries;
	private List<AdapterItem> genders;
	
	private FixedLists(){
		
	}
	
	public static FixedLists getInstance(){
		return instance;
	}
	
	/**
	 * iso country codes with the names according to locale
	 */
	
	
	public List<AdapterItem> getCountries(Context context){
		if (countries == null){
			countries = new ArrayList<AdapterItem>();
			countries.add(new SimpleSpinnerItem("AD", context.getString(R.string.country_AD)));
			countries.add(new SimpleSpinnerItem("AE", context.getString(R.string.country_AE)));
			countries.add(new SimpleSpinnerItem("AF", context.getString(R.string.country_AF)));
			countries.add(new SimpleSpinnerItem("AG", context.getString(R.string.country_AG)));
			countries.add(new SimpleSpinnerItem("AL", context.getString(R.string.country_AL)));
			countries.add(new SimpleSpinnerItem("AM", context.getString(R.string.country_AM)));
			countries.add(new SimpleSpinnerItem("AO", context.getString(R.string.country_AO)));
			countries.add(new SimpleSpinnerItem("AR", context.getString(R.string.country_AR)));
			countries.add(new SimpleSpinnerItem("AT", context.getString(R.string.country_AT)));
			countries.add(new SimpleSpinnerItem("AU", context.getString(R.string.country_AU)));
			countries.add(new SimpleSpinnerItem("AZ", context.getString(R.string.country_AZ)));
			countries.add(new SimpleSpinnerItem("BA", context.getString(R.string.country_BA)));
			countries.add(new SimpleSpinnerItem("BB", context.getString(R.string.country_BB)));
			countries.add(new SimpleSpinnerItem("BD", context.getString(R.string.country_BD)));
			countries.add(new SimpleSpinnerItem("BE", context.getString(R.string.country_BE)));
			countries.add(new SimpleSpinnerItem("BF", context.getString(R.string.country_BF)));
			countries.add(new SimpleSpinnerItem("BG", context.getString(R.string.country_BG)));
			countries.add(new SimpleSpinnerItem("BH", context.getString(R.string.country_BH)));
			countries.add(new SimpleSpinnerItem("BI", context.getString(R.string.country_BI)));
			countries.add(new SimpleSpinnerItem("BJ", context.getString(R.string.country_BJ)));
			countries.add(new SimpleSpinnerItem("BN", context.getString(R.string.country_BN)));
			countries.add(new SimpleSpinnerItem("BO", context.getString(R.string.country_BO)));
			countries.add(new SimpleSpinnerItem("BR", context.getString(R.string.country_BR)));
			countries.add(new SimpleSpinnerItem("BS", context.getString(R.string.country_BS)));
			countries.add(new SimpleSpinnerItem("BT", context.getString(R.string.country_BT)));
			countries.add(new SimpleSpinnerItem("BW", context.getString(R.string.country_BW)));
			countries.add(new SimpleSpinnerItem("BY", context.getString(R.string.country_BY)));
			countries.add(new SimpleSpinnerItem("BZ", context.getString(R.string.country_BZ)));
			countries.add(new SimpleSpinnerItem("CA", context.getString(R.string.country_CA)));
			countries.add(new SimpleSpinnerItem("CD", context.getString(R.string.country_CD)));
			countries.add(new SimpleSpinnerItem("CF", context.getString(R.string.country_CF)));
			countries.add(new SimpleSpinnerItem("CG", context.getString(R.string.country_CG)));
			countries.add(new SimpleSpinnerItem("CH", context.getString(R.string.country_CH)));
			countries.add(new SimpleSpinnerItem("CI", context.getString(R.string.country_CI)));
			countries.add(new SimpleSpinnerItem("CL", context.getString(R.string.country_CL)));
			countries.add(new SimpleSpinnerItem("CM", context.getString(R.string.country_CM)));
			countries.add(new SimpleSpinnerItem("CN", context.getString(R.string.country_CN)));
			countries.add(new SimpleSpinnerItem("CO", context.getString(R.string.country_CO)));
			countries.add(new SimpleSpinnerItem("CR", context.getString(R.string.country_CR)));
			countries.add(new SimpleSpinnerItem("CU", context.getString(R.string.country_CU)));
			countries.add(new SimpleSpinnerItem("CV", context.getString(R.string.country_CV)));
			countries.add(new SimpleSpinnerItem("CY", context.getString(R.string.country_CY)));
			countries.add(new SimpleSpinnerItem("CZ", context.getString(R.string.country_CZ)));
			countries.add(new SimpleSpinnerItem("DE", context.getString(R.string.country_DE)));
			countries.add(new SimpleSpinnerItem("DJ", context.getString(R.string.country_DJ)));
			countries.add(new SimpleSpinnerItem("DK", context.getString(R.string.country_DK)));
			countries.add(new SimpleSpinnerItem("DM", context.getString(R.string.country_DM)));
			countries.add(new SimpleSpinnerItem("DO", context.getString(R.string.country_DO)));
			countries.add(new SimpleSpinnerItem("DZ", context.getString(R.string.country_DZ)));
			countries.add(new SimpleSpinnerItem("EC", context.getString(R.string.country_EC)));
			countries.add(new SimpleSpinnerItem("EE", context.getString(R.string.country_EE)));
			countries.add(new SimpleSpinnerItem("EG", context.getString(R.string.country_EG)));
			countries.add(new SimpleSpinnerItem("ER", context.getString(R.string.country_ER)));
			countries.add(new SimpleSpinnerItem("ES", context.getString(R.string.country_ES)));
			countries.add(new SimpleSpinnerItem("ET", context.getString(R.string.country_ET)));
			countries.add(new SimpleSpinnerItem("FI", context.getString(R.string.country_FI)));
			countries.add(new SimpleSpinnerItem("FJ", context.getString(R.string.country_FJ)));
			countries.add(new SimpleSpinnerItem("FR", context.getString(R.string.country_FR)));
			countries.add(new SimpleSpinnerItem("GA", context.getString(R.string.country_GA)));
			countries.add(new SimpleSpinnerItem("GB", context.getString(R.string.country_GB)));
			countries.add(new SimpleSpinnerItem("GD", context.getString(R.string.country_GD)));
			countries.add(new SimpleSpinnerItem("GE", context.getString(R.string.country_GE)));
			countries.add(new SimpleSpinnerItem("GH", context.getString(R.string.country_GH)));
			countries.add(new SimpleSpinnerItem("GM", context.getString(R.string.country_GM)));
			countries.add(new SimpleSpinnerItem("GN", context.getString(R.string.country_GN)));
			countries.add(new SimpleSpinnerItem("GQ", context.getString(R.string.country_GQ)));
			countries.add(new SimpleSpinnerItem("GR", context.getString(R.string.country_GR)));
			countries.add(new SimpleSpinnerItem("GT", context.getString(R.string.country_GT)));
			countries.add(new SimpleSpinnerItem("GW", context.getString(R.string.country_GW)));
			countries.add(new SimpleSpinnerItem("GY", context.getString(R.string.country_GY)));
			countries.add(new SimpleSpinnerItem("HK", context.getString(R.string.country_HK)));
			countries.add(new SimpleSpinnerItem("HN", context.getString(R.string.country_HN)));
			countries.add(new SimpleSpinnerItem("HR", context.getString(R.string.country_HR)));
			countries.add(new SimpleSpinnerItem("HT", context.getString(R.string.country_HT)));
			countries.add(new SimpleSpinnerItem("HU", context.getString(R.string.country_HU)));
			countries.add(new SimpleSpinnerItem("ID", context.getString(R.string.country_ID)));
			countries.add(new SimpleSpinnerItem("IE", context.getString(R.string.country_IE)));
			countries.add(new SimpleSpinnerItem("IL", context.getString(R.string.country_IL)));
			countries.add(new SimpleSpinnerItem("IN", context.getString(R.string.country_IN)));
			countries.add(new SimpleSpinnerItem("IQ", context.getString(R.string.country_IQ)));
			countries.add(new SimpleSpinnerItem("IR", context.getString(R.string.country_IR)));
			countries.add(new SimpleSpinnerItem("IS", context.getString(R.string.country_IS)));
			countries.add(new SimpleSpinnerItem("IT", context.getString(R.string.country_IT)));
			countries.add(new SimpleSpinnerItem("JM", context.getString(R.string.country_JM)));
			countries.add(new SimpleSpinnerItem("JO", context.getString(R.string.country_JO)));
			countries.add(new SimpleSpinnerItem("JP", context.getString(R.string.country_JP)));
			countries.add(new SimpleSpinnerItem("KE", context.getString(R.string.country_KE)));
			countries.add(new SimpleSpinnerItem("KG", context.getString(R.string.country_KG)));
			countries.add(new SimpleSpinnerItem("KH", context.getString(R.string.country_KH)));
			countries.add(new SimpleSpinnerItem("KI", context.getString(R.string.country_KI)));
			countries.add(new SimpleSpinnerItem("KM", context.getString(R.string.country_KM)));
			countries.add(new SimpleSpinnerItem("KN", context.getString(R.string.country_KN)));
			countries.add(new SimpleSpinnerItem("KP", context.getString(R.string.country_KP)));
			countries.add(new SimpleSpinnerItem("KR", context.getString(R.string.country_KR)));
			countries.add(new SimpleSpinnerItem("KV", context.getString(R.string.country_KV)));
			countries.add(new SimpleSpinnerItem("KW", context.getString(R.string.country_KW)));
			countries.add(new SimpleSpinnerItem("KZ", context.getString(R.string.country_KZ)));
			countries.add(new SimpleSpinnerItem("LA", context.getString(R.string.country_LA)));
			countries.add(new SimpleSpinnerItem("LB", context.getString(R.string.country_LB)));
			countries.add(new SimpleSpinnerItem("LC", context.getString(R.string.country_LC)));
			countries.add(new SimpleSpinnerItem("LI", context.getString(R.string.country_LI)));
			countries.add(new SimpleSpinnerItem("LK", context.getString(R.string.country_LK)));
			countries.add(new SimpleSpinnerItem("LR", context.getString(R.string.country_LR)));
			countries.add(new SimpleSpinnerItem("LS", context.getString(R.string.country_LS)));
			countries.add(new SimpleSpinnerItem("LT", context.getString(R.string.country_LT)));
			countries.add(new SimpleSpinnerItem("LU", context.getString(R.string.country_LU)));
			countries.add(new SimpleSpinnerItem("LV", context.getString(R.string.country_LV)));
			countries.add(new SimpleSpinnerItem("LY", context.getString(R.string.country_LY)));
			countries.add(new SimpleSpinnerItem("MA", context.getString(R.string.country_MA)));
			countries.add(new SimpleSpinnerItem("MC", context.getString(R.string.country_MC)));
			countries.add(new SimpleSpinnerItem("MD", context.getString(R.string.country_MD)));
			countries.add(new SimpleSpinnerItem("ME", context.getString(R.string.country_ME)));
			countries.add(new SimpleSpinnerItem("MG", context.getString(R.string.country_MG)));
			countries.add(new SimpleSpinnerItem("MH", context.getString(R.string.country_MH)));
			countries.add(new SimpleSpinnerItem("MK", context.getString(R.string.country_MK)));
			countries.add(new SimpleSpinnerItem("ML", context.getString(R.string.country_ML)));
			countries.add(new SimpleSpinnerItem("MM", context.getString(R.string.country_MM)));
			countries.add(new SimpleSpinnerItem("MN", context.getString(R.string.country_MN)));
			countries.add(new SimpleSpinnerItem("MR", context.getString(R.string.country_MR)));
			countries.add(new SimpleSpinnerItem("MT", context.getString(R.string.country_MT)));
			countries.add(new SimpleSpinnerItem("MU", context.getString(R.string.country_MU)));
			countries.add(new SimpleSpinnerItem("MV", context.getString(R.string.country_MV)));
			countries.add(new SimpleSpinnerItem("MW", context.getString(R.string.country_MW)));
			countries.add(new SimpleSpinnerItem("MX", context.getString(R.string.country_MX)));
			countries.add(new SimpleSpinnerItem("MY", context.getString(R.string.country_MY)));
			countries.add(new SimpleSpinnerItem("MZ", context.getString(R.string.country_MZ)));
			countries.add(new SimpleSpinnerItem("NA", context.getString(R.string.country_NA)));
			countries.add(new SimpleSpinnerItem("NE", context.getString(R.string.country_NE)));
			countries.add(new SimpleSpinnerItem("NG", context.getString(R.string.country_NG)));
			countries.add(new SimpleSpinnerItem("NI", context.getString(R.string.country_NI)));
			countries.add(new SimpleSpinnerItem("NL", context.getString(R.string.country_NL)));
			countries.add(new SimpleSpinnerItem("NO", context.getString(R.string.country_NO)));
			countries.add(new SimpleSpinnerItem("NP", context.getString(R.string.country_NP)));
			countries.add(new SimpleSpinnerItem("NR", context.getString(R.string.country_NR)));
			countries.add(new SimpleSpinnerItem("NZ", context.getString(R.string.country_NZ)));
			countries.add(new SimpleSpinnerItem("OM", context.getString(R.string.country_OM)));
			countries.add(new SimpleSpinnerItem("PA", context.getString(R.string.country_PA)));
			countries.add(new SimpleSpinnerItem("PE", context.getString(R.string.country_PE)));
			countries.add(new SimpleSpinnerItem("PG", context.getString(R.string.country_PG)));
			countries.add(new SimpleSpinnerItem("PH", context.getString(R.string.country_PH)));
			countries.add(new SimpleSpinnerItem("PK", context.getString(R.string.country_PK)));
			countries.add(new SimpleSpinnerItem("PL", context.getString(R.string.country_PL)));
			countries.add(new SimpleSpinnerItem("PS", context.getString(R.string.country_PS)));
			countries.add(new SimpleSpinnerItem("PT", context.getString(R.string.country_PT)));
			countries.add(new SimpleSpinnerItem("PW", context.getString(R.string.country_PW)));
			countries.add(new SimpleSpinnerItem("PY", context.getString(R.string.country_PY)));
			countries.add(new SimpleSpinnerItem("QA", context.getString(R.string.country_QA)));
			countries.add(new SimpleSpinnerItem("RO", context.getString(R.string.country_RO)));
			countries.add(new SimpleSpinnerItem("RS", context.getString(R.string.country_RS)));
			countries.add(new SimpleSpinnerItem("RU", context.getString(R.string.country_RU)));
			countries.add(new SimpleSpinnerItem("RW", context.getString(R.string.country_RW)));
			countries.add(new SimpleSpinnerItem("SA", context.getString(R.string.country_SA)));
			countries.add(new SimpleSpinnerItem("SB", context.getString(R.string.country_SB)));
			countries.add(new SimpleSpinnerItem("SC", context.getString(R.string.country_SC)));
			countries.add(new SimpleSpinnerItem("SD", context.getString(R.string.country_SD)));
			countries.add(new SimpleSpinnerItem("SE", context.getString(R.string.country_SE)));
			countries.add(new SimpleSpinnerItem("SG", context.getString(R.string.country_SG)));
			countries.add(new SimpleSpinnerItem("SI", context.getString(R.string.country_SI)));
			countries.add(new SimpleSpinnerItem("SK", context.getString(R.string.country_SK)));
			countries.add(new SimpleSpinnerItem("SL", context.getString(R.string.country_SL)));
			countries.add(new SimpleSpinnerItem("SM", context.getString(R.string.country_SM)));
			countries.add(new SimpleSpinnerItem("SN", context.getString(R.string.country_SN)));
			countries.add(new SimpleSpinnerItem("SO", context.getString(R.string.country_SO)));
			countries.add(new SimpleSpinnerItem("SR", context.getString(R.string.country_SR)));
			countries.add(new SimpleSpinnerItem("ST", context.getString(R.string.country_ST)));
			countries.add(new SimpleSpinnerItem("SV", context.getString(R.string.country_SV)));
			countries.add(new SimpleSpinnerItem("SY", context.getString(R.string.country_SY)));
			countries.add(new SimpleSpinnerItem("SZ", context.getString(R.string.country_SZ)));
			countries.add(new SimpleSpinnerItem("TD", context.getString(R.string.country_TD)));
			countries.add(new SimpleSpinnerItem("TG", context.getString(R.string.country_TG)));
			countries.add(new SimpleSpinnerItem("TH", context.getString(R.string.country_TH)));
			countries.add(new SimpleSpinnerItem("TJ", context.getString(R.string.country_TJ)));
			countries.add(new SimpleSpinnerItem("TL", context.getString(R.string.country_TL)));
			countries.add(new SimpleSpinnerItem("TM", context.getString(R.string.country_TM)));
			countries.add(new SimpleSpinnerItem("TN", context.getString(R.string.country_TN)));
			countries.add(new SimpleSpinnerItem("TO", context.getString(R.string.country_TO)));
			countries.add(new SimpleSpinnerItem("TR", context.getString(R.string.country_TR)));
			countries.add(new SimpleSpinnerItem("TT", context.getString(R.string.country_TT)));
			countries.add(new SimpleSpinnerItem("TV", context.getString(R.string.country_TV)));
			countries.add(new SimpleSpinnerItem("TW", context.getString(R.string.country_TW)));
			countries.add(new SimpleSpinnerItem("TZ", context.getString(R.string.country_TZ)));
			countries.add(new SimpleSpinnerItem("UA", context.getString(R.string.country_UA)));
			countries.add(new SimpleSpinnerItem("UG", context.getString(R.string.country_UG)));
			countries.add(new SimpleSpinnerItem("US", context.getString(R.string.country_US)));
			countries.add(new SimpleSpinnerItem("UY", context.getString(R.string.country_UY)));
			countries.add(new SimpleSpinnerItem("UZ", context.getString(R.string.country_UZ)));
			countries.add(new SimpleSpinnerItem("VA", context.getString(R.string.country_VA)));
			countries.add(new SimpleSpinnerItem("VC", context.getString(R.string.country_VC)));
			countries.add(new SimpleSpinnerItem("VE", context.getString(R.string.country_VE)));
			countries.add(new SimpleSpinnerItem("VN", context.getString(R.string.country_VN)));
			countries.add(new SimpleSpinnerItem("VU", context.getString(R.string.country_VU)));
			countries.add(new SimpleSpinnerItem("WS", context.getString(R.string.country_WS)));
			countries.add(new SimpleSpinnerItem("YE", context.getString(R.string.country_YE)));
			countries.add(new SimpleSpinnerItem("ZA", context.getString(R.string.country_ZA)));
			countries.add(new SimpleSpinnerItem("ZM", context.getString(R.string.country_ZM)));
			countries.add(new SimpleSpinnerItem("ZW", context.getString(R.string.country_ZW)));
		}
		return countries;
	}
	
	/**
	 * iso language codes with the names according to locale
	 */
	
	public List<AdapterItem> getLanguages(Context context){
		if (languages == null){
			languages = new ArrayList<AdapterItem>();
			languages.add(new SimpleSpinnerItem("de", context.getString(R.string.language_de)));
			languages.add(new SimpleSpinnerItem("en", context.getString(R.string.language_en)));
			languages.add(new SimpleSpinnerItem("es", context.getString(R.string.language_es)));
			languages.add(new SimpleSpinnerItem("fr", context.getString(R.string.language_fr)));
			languages.add(new SimpleSpinnerItem("it", context.getString(R.string.language_it)));
		}
		return languages;
	}
	
	/**
	 * gender codes with the names according to locale
	 */
	
	public List<AdapterItem> getGenders (Context context){
		if (genders == null){
			genders = new ArrayList<AdapterItem>();
			genders.add(new SimpleSpinnerItem("f", context.getString(R.string.gender_f)));
			genders.add(new SimpleSpinnerItem("m", context.getString(R.string.gender_m)));
			genders.add(new SimpleSpinnerItem("u", context.getString(R.string.gender_u)));
		}
		return genders;
	}
}
