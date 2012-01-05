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
 * as we need these values and everything is still beta i just put them in enums and for german only
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
			countries.add(new Country("AD", context.getString(R.string.country_AD)));
			countries.add(new Country("AE", context.getString(R.string.country_AE)));
			countries.add(new Country("AF", context.getString(R.string.country_AF)));
			countries.add(new Country("AG", context.getString(R.string.country_AG)));
			countries.add(new Country("AL", context.getString(R.string.country_AL)));
			countries.add(new Country("AM", context.getString(R.string.country_AM)));
			countries.add(new Country("AO", context.getString(R.string.country_AO)));
			countries.add(new Country("AR", context.getString(R.string.country_AR)));
			countries.add(new Country("AT", context.getString(R.string.country_AT)));
			countries.add(new Country("AU", context.getString(R.string.country_AU)));
			countries.add(new Country("AZ", context.getString(R.string.country_AZ)));
			countries.add(new Country("BA", context.getString(R.string.country_BA)));
			countries.add(new Country("BB", context.getString(R.string.country_BB)));
			countries.add(new Country("BD", context.getString(R.string.country_BD)));
			countries.add(new Country("BE", context.getString(R.string.country_BE)));
			countries.add(new Country("BF", context.getString(R.string.country_BF)));
			countries.add(new Country("BG", context.getString(R.string.country_BG)));
			countries.add(new Country("BH", context.getString(R.string.country_BH)));
			countries.add(new Country("BI", context.getString(R.string.country_BI)));
			countries.add(new Country("BJ", context.getString(R.string.country_BJ)));
			countries.add(new Country("BN", context.getString(R.string.country_BN)));
			countries.add(new Country("BO", context.getString(R.string.country_BO)));
			countries.add(new Country("BR", context.getString(R.string.country_BR)));
			countries.add(new Country("BS", context.getString(R.string.country_BS)));
			countries.add(new Country("BT", context.getString(R.string.country_BT)));
			countries.add(new Country("BW", context.getString(R.string.country_BW)));
			countries.add(new Country("BY", context.getString(R.string.country_BY)));
			countries.add(new Country("BZ", context.getString(R.string.country_BZ)));
			countries.add(new Country("CA", context.getString(R.string.country_CA)));
			countries.add(new Country("CD", context.getString(R.string.country_CD)));
			countries.add(new Country("CF", context.getString(R.string.country_CF)));
			countries.add(new Country("CG", context.getString(R.string.country_CG)));
			countries.add(new Country("CH", context.getString(R.string.country_CH)));
			countries.add(new Country("CI", context.getString(R.string.country_CI)));
			countries.add(new Country("CL", context.getString(R.string.country_CL)));
			countries.add(new Country("CM", context.getString(R.string.country_CM)));
			countries.add(new Country("CN", context.getString(R.string.country_CN)));
			countries.add(new Country("CO", context.getString(R.string.country_CO)));
			countries.add(new Country("CR", context.getString(R.string.country_CR)));
			countries.add(new Country("CU", context.getString(R.string.country_CU)));
			countries.add(new Country("CV", context.getString(R.string.country_CV)));
			countries.add(new Country("CY", context.getString(R.string.country_CY)));
			countries.add(new Country("CZ", context.getString(R.string.country_CZ)));
			countries.add(new Country("DE", context.getString(R.string.country_DE)));
			countries.add(new Country("DJ", context.getString(R.string.country_DJ)));
			countries.add(new Country("DK", context.getString(R.string.country_DK)));
			countries.add(new Country("DM", context.getString(R.string.country_DM)));
			countries.add(new Country("DO", context.getString(R.string.country_DO)));
			countries.add(new Country("DZ", context.getString(R.string.country_DZ)));
			countries.add(new Country("EC", context.getString(R.string.country_EC)));
			countries.add(new Country("EE", context.getString(R.string.country_EE)));
			countries.add(new Country("EG", context.getString(R.string.country_EG)));
			countries.add(new Country("ER", context.getString(R.string.country_ER)));
			countries.add(new Country("ES", context.getString(R.string.country_ES)));
			countries.add(new Country("ET", context.getString(R.string.country_ET)));
			countries.add(new Country("FI", context.getString(R.string.country_FI)));
			countries.add(new Country("FJ", context.getString(R.string.country_FJ)));
			countries.add(new Country("FR", context.getString(R.string.country_FR)));
			countries.add(new Country("GA", context.getString(R.string.country_GA)));
			countries.add(new Country("GB", context.getString(R.string.country_GB)));
			countries.add(new Country("GD", context.getString(R.string.country_GD)));
			countries.add(new Country("GE", context.getString(R.string.country_GE)));
			countries.add(new Country("GH", context.getString(R.string.country_GH)));
			countries.add(new Country("GM", context.getString(R.string.country_GM)));
			countries.add(new Country("GN", context.getString(R.string.country_GN)));
			countries.add(new Country("GQ", context.getString(R.string.country_GQ)));
			countries.add(new Country("GR", context.getString(R.string.country_GR)));
			countries.add(new Country("GT", context.getString(R.string.country_GT)));
			countries.add(new Country("GW", context.getString(R.string.country_GW)));
			countries.add(new Country("GY", context.getString(R.string.country_GY)));
			countries.add(new Country("HK", context.getString(R.string.country_HK)));
			countries.add(new Country("HN", context.getString(R.string.country_HN)));
			countries.add(new Country("HR", context.getString(R.string.country_HR)));
			countries.add(new Country("HT", context.getString(R.string.country_HT)));
			countries.add(new Country("HU", context.getString(R.string.country_HU)));
			countries.add(new Country("ID", context.getString(R.string.country_ID)));
			countries.add(new Country("IE", context.getString(R.string.country_IE)));
			countries.add(new Country("IL", context.getString(R.string.country_IL)));
			countries.add(new Country("IN", context.getString(R.string.country_IN)));
			countries.add(new Country("IQ", context.getString(R.string.country_IQ)));
			countries.add(new Country("IR", context.getString(R.string.country_IR)));
			countries.add(new Country("IS", context.getString(R.string.country_IS)));
			countries.add(new Country("IT", context.getString(R.string.country_IT)));
			countries.add(new Country("JM", context.getString(R.string.country_JM)));
			countries.add(new Country("JO", context.getString(R.string.country_JO)));
			countries.add(new Country("JP", context.getString(R.string.country_JP)));
			countries.add(new Country("KE", context.getString(R.string.country_KE)));
			countries.add(new Country("KG", context.getString(R.string.country_KG)));
			countries.add(new Country("KH", context.getString(R.string.country_KH)));
			countries.add(new Country("KI", context.getString(R.string.country_KI)));
			countries.add(new Country("KM", context.getString(R.string.country_KM)));
			countries.add(new Country("KN", context.getString(R.string.country_KN)));
			countries.add(new Country("KP", context.getString(R.string.country_KP)));
			countries.add(new Country("KR", context.getString(R.string.country_KR)));
			countries.add(new Country("KV", context.getString(R.string.country_KV)));
			countries.add(new Country("KW", context.getString(R.string.country_KW)));
			countries.add(new Country("KZ", context.getString(R.string.country_KZ)));
			countries.add(new Country("LA", context.getString(R.string.country_LA)));
			countries.add(new Country("LB", context.getString(R.string.country_LB)));
			countries.add(new Country("LC", context.getString(R.string.country_LC)));
			countries.add(new Country("LI", context.getString(R.string.country_LI)));
			countries.add(new Country("LK", context.getString(R.string.country_LK)));
			countries.add(new Country("LR", context.getString(R.string.country_LR)));
			countries.add(new Country("LS", context.getString(R.string.country_LS)));
			countries.add(new Country("LT", context.getString(R.string.country_LT)));
			countries.add(new Country("LU", context.getString(R.string.country_LU)));
			countries.add(new Country("LV", context.getString(R.string.country_LV)));
			countries.add(new Country("LY", context.getString(R.string.country_LY)));
			countries.add(new Country("MA", context.getString(R.string.country_MA)));
			countries.add(new Country("MC", context.getString(R.string.country_MC)));
			countries.add(new Country("MD", context.getString(R.string.country_MD)));
			countries.add(new Country("ME", context.getString(R.string.country_ME)));
			countries.add(new Country("MG", context.getString(R.string.country_MG)));
			countries.add(new Country("MH", context.getString(R.string.country_MH)));
			countries.add(new Country("MK", context.getString(R.string.country_MK)));
			countries.add(new Country("ML", context.getString(R.string.country_ML)));
			countries.add(new Country("MM", context.getString(R.string.country_MM)));
			countries.add(new Country("MN", context.getString(R.string.country_MN)));
			countries.add(new Country("MR", context.getString(R.string.country_MR)));
			countries.add(new Country("MT", context.getString(R.string.country_MT)));
			countries.add(new Country("MU", context.getString(R.string.country_MU)));
			countries.add(new Country("MV", context.getString(R.string.country_MV)));
			countries.add(new Country("MW", context.getString(R.string.country_MW)));
			countries.add(new Country("MX", context.getString(R.string.country_MX)));
			countries.add(new Country("MY", context.getString(R.string.country_MY)));
			countries.add(new Country("MZ", context.getString(R.string.country_MZ)));
			countries.add(new Country("NA", context.getString(R.string.country_NA)));
			countries.add(new Country("NE", context.getString(R.string.country_NE)));
			countries.add(new Country("NG", context.getString(R.string.country_NG)));
			countries.add(new Country("NI", context.getString(R.string.country_NI)));
			countries.add(new Country("NL", context.getString(R.string.country_NL)));
			countries.add(new Country("NO", context.getString(R.string.country_NO)));
			countries.add(new Country("NP", context.getString(R.string.country_NP)));
			countries.add(new Country("NR", context.getString(R.string.country_NR)));
			countries.add(new Country("NZ", context.getString(R.string.country_NZ)));
			countries.add(new Country("OM", context.getString(R.string.country_OM)));
			countries.add(new Country("PA", context.getString(R.string.country_PA)));
			countries.add(new Country("PE", context.getString(R.string.country_PE)));
			countries.add(new Country("PG", context.getString(R.string.country_PG)));
			countries.add(new Country("PH", context.getString(R.string.country_PH)));
			countries.add(new Country("PK", context.getString(R.string.country_PK)));
			countries.add(new Country("PL", context.getString(R.string.country_PL)));
			countries.add(new Country("PS", context.getString(R.string.country_PS)));
			countries.add(new Country("PT", context.getString(R.string.country_PT)));
			countries.add(new Country("PW", context.getString(R.string.country_PW)));
			countries.add(new Country("PY", context.getString(R.string.country_PY)));
			countries.add(new Country("QA", context.getString(R.string.country_QA)));
			countries.add(new Country("RO", context.getString(R.string.country_RO)));
			countries.add(new Country("RS", context.getString(R.string.country_RS)));
			countries.add(new Country("RU", context.getString(R.string.country_RU)));
			countries.add(new Country("RW", context.getString(R.string.country_RW)));
			countries.add(new Country("SA", context.getString(R.string.country_SA)));
			countries.add(new Country("SB", context.getString(R.string.country_SB)));
			countries.add(new Country("SC", context.getString(R.string.country_SC)));
			countries.add(new Country("SD", context.getString(R.string.country_SD)));
			countries.add(new Country("SE", context.getString(R.string.country_SE)));
			countries.add(new Country("SG", context.getString(R.string.country_SG)));
			countries.add(new Country("SI", context.getString(R.string.country_SI)));
			countries.add(new Country("SK", context.getString(R.string.country_SK)));
			countries.add(new Country("SL", context.getString(R.string.country_SL)));
			countries.add(new Country("SM", context.getString(R.string.country_SM)));
			countries.add(new Country("SN", context.getString(R.string.country_SN)));
			countries.add(new Country("SO", context.getString(R.string.country_SO)));
			countries.add(new Country("SR", context.getString(R.string.country_SR)));
			countries.add(new Country("ST", context.getString(R.string.country_ST)));
			countries.add(new Country("SV", context.getString(R.string.country_SV)));
			countries.add(new Country("SY", context.getString(R.string.country_SY)));
			countries.add(new Country("SZ", context.getString(R.string.country_SZ)));
			countries.add(new Country("TD", context.getString(R.string.country_TD)));
			countries.add(new Country("TG", context.getString(R.string.country_TG)));
			countries.add(new Country("TH", context.getString(R.string.country_TH)));
			countries.add(new Country("TJ", context.getString(R.string.country_TJ)));
			countries.add(new Country("TL", context.getString(R.string.country_TL)));
			countries.add(new Country("TM", context.getString(R.string.country_TM)));
			countries.add(new Country("TN", context.getString(R.string.country_TN)));
			countries.add(new Country("TO", context.getString(R.string.country_TO)));
			countries.add(new Country("TR", context.getString(R.string.country_TR)));
			countries.add(new Country("TT", context.getString(R.string.country_TT)));
			countries.add(new Country("TV", context.getString(R.string.country_TV)));
			countries.add(new Country("TW", context.getString(R.string.country_TW)));
			countries.add(new Country("TZ", context.getString(R.string.country_TZ)));
			countries.add(new Country("UA", context.getString(R.string.country_UA)));
			countries.add(new Country("UG", context.getString(R.string.country_UG)));
			countries.add(new Country("US", context.getString(R.string.country_US)));
			countries.add(new Country("UY", context.getString(R.string.country_UY)));
			countries.add(new Country("UZ", context.getString(R.string.country_UZ)));
			countries.add(new Country("VA", context.getString(R.string.country_VA)));
			countries.add(new Country("VC", context.getString(R.string.country_VC)));
			countries.add(new Country("VE", context.getString(R.string.country_VE)));
			countries.add(new Country("VN", context.getString(R.string.country_VN)));
			countries.add(new Country("VU", context.getString(R.string.country_VU)));
			countries.add(new Country("WS", context.getString(R.string.country_WS)));
			countries.add(new Country("YE", context.getString(R.string.country_YE)));
			countries.add(new Country("ZA", context.getString(R.string.country_ZA)));
			countries.add(new Country("ZM", context.getString(R.string.country_ZM)));
			countries.add(new Country("ZW", context.getString(R.string.country_ZW)));
		}
		return countries;
	}
	
	/**
	 * iso language codes with the names according to locale
	 */
	
	public List<AdapterItem> getLanguages(Context context){
		if (languages == null){
			languages = new ArrayList<AdapterItem>();
			languages.add(new Language("de", context.getString(R.string.language_de)));
			languages.add(new Language("en", context.getString(R.string.language_en)));
			languages.add(new Language("es", context.getString(R.string.language_es)));
			languages.add(new Language("fr", context.getString(R.string.language_fr)));
			languages.add(new Language("it", context.getString(R.string.language_it)));
		}
		return languages;
	}
	
	/**
	 * gender codes with the names according to locale
	 */
	
	public List<AdapterItem> getGenders (Context context){
		if (genders == null){
			genders = new ArrayList<AdapterItem>();
			genders.add(new Language("f", context.getString(R.string.gender_f)));
			genders.add(new Language("m", context.getString(R.string.gender_m)));
			genders.add(new Language("u", context.getString(R.string.gender_u)));
		}
		return genders;
	}
}
