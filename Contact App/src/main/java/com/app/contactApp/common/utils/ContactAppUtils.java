package com.app.contactApp.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.app.contactApp.data.entity.AddressType;
import com.app.contactApp.data.entity.Country;
import com.app.contactApp.data.entity.CountryCode;
import com.app.contactApp.data.entity.District;
import com.app.contactApp.data.entity.State;
import com.app.contactApp.data.entity.ZipCode;

@EnableScheduling
@Component
public class ContactAppUtils {

	private static List<AddressType> addressTypeList = new ArrayList<>();
	private static HashMap<String, AddressType> addressTypeMap = new HashMap<>();
	private static List<CountryCode> countryCodesList = new ArrayList<>();
	private static HashMap<Long, CountryCode> coutryCodesMap = new HashMap<>();
	private static List<Country> countryList = new ArrayList<>();
	private static HashMap<Long, Country> countryMap = new HashMap<>();
	private static HashMap<Long, State> stateMap = new HashMap<>();
	private static HashMap<Long, District> districtMap = new HashMap<>();
	private static HashMap<Long, List<ZipCode>> zipCodeListMap = new HashMap<>();
	private static HashMap<Long, ZipCode> zipCodeMap = new HashMap<>();

	public static void initData(List<AddressType> addressTypes, List<Country> countries,
			List<CountryCode> countryCodes) {
		System.out.println("Stating Init Data");
		if (addressTypes != null)
			addressTypeList = addressTypes;
		if (countryCodes != null)
			countryCodesList = countryCodes;
		if (countries != null)
			countryList = countries;

		for (AddressType addressType : getAddressTypeList()) {
			getAddressTypeMap().put(addressType.getAddressType(), addressType);
		}
		for (CountryCode countryCode : getCountryCodesList()) {
			getCoutryCodesMap().put(countryCode.getCountryCodeId(), countryCode);
		}
		for (Country country : getCountryList()) {
			getCountryMap().put(country.getCountryId(), country);
			for (State state : country.getStates()) {
				getStateMap().put(state.getStateId(), state);
				for (District district : state.getDistricts()) {
					getDistrictMap().put(district.getDistrictId(), district);
					for (ZipCode zipCode : district.getZipCodes()) {
						getZipCodeMap().put(zipCode.getZipId(), zipCode);
						if (getZipCodeListMap().get(zipCode.getZipCode()) != null) {
							getZipCodeListMap().get(zipCode.getZipCode()).add(zipCode);
						} else {
							List<ZipCode> zipList = new ArrayList<>();
							zipList.add(zipCode);
							getZipCodeListMap().put(zipCode.getZipCode(), zipList);
						}
					}
				}
			}
		}
		System.out.println("Init Data Complete");
		System.out.println("Address Type List : " + getAddressTypeList().size());
		System.out.println("Address Type Map : " + getAddressTypeMap().size());
		System.out.println("Country Code List : " + getCountryCodesList().size());
		System.out.println("Country Code Map : " + getCoutryCodesMap().size());
		System.out.println("Country List : " + getCountryList().size());
		System.out.println("Country Map : " + getCountryMap().size());
		System.out.println("State Map : " + getStateMap().size());
		System.out.println("District Map : " + getDistrictMap().size());
		System.out.println("ZipCodeList Map : " + getZipCodeListMap().size());
		System.out.println("Zip List Map : " + getZipCodeMap().size());
	}

	public static AddressType getAddressType(String addressType) {
		return getAddressTypeMap().get(addressType);
	}

	public static List<AddressType> getAddressTypeList() {
		return addressTypeList;
	}

	public static HashMap<String, AddressType> getAddressTypeMap() {
		return addressTypeMap;
	}

	public static List<CountryCode> getCountryCodesList() {
		return countryCodesList;
	}

	public static CountryCode getCountryCode(Long countryCodeId) {
		return coutryCodesMap.get(countryCodeId);
	}

	public static List<Country> getCountryList() {
		return countryList;
	}

	public static Country getCountry(Long countryId) {
		return getCountryMap().get(countryId);
	}

	public static State getState(Long stateId) {
		return getStateMap().get(stateId);
	}

	public static District getDistrict(Long districtId) {
		return getDistrictMap().get(districtId);
	}

	public static List<ZipCode> getZipCodeList(Long zipCode) {
		return getZipCodeListMap().get(zipCode);
	}

	public static ZipCode getZipCode(Long zipId) {
		return getZipCodeMap().get(zipId);
	}

	public static HashMap<Long, CountryCode> getCoutryCodesMap() {
		return coutryCodesMap;
	}

	public static HashMap<Long, Country> getCountryMap() {
		return countryMap;
	}

	public static HashMap<Long, State> getStateMap() {
		return stateMap;
	}

	public static HashMap<Long, District> getDistrictMap() {
		return districtMap;
	}

	public static HashMap<Long, List<ZipCode>> getZipCodeListMap() {
		return zipCodeListMap;
	}

	public static HashMap<Long, ZipCode> getZipCodeMap() {
		return zipCodeMap;
	}

	public static boolean isAuthorized(HttpSession session) {
		if (session.getAttribute(Constants.CURRENT_USER) != null)
			return true;
		return false;
	}

	public static String concatenate(List<String> listOfItems, String separator) {
		StringBuilder sb = new StringBuilder();
		Iterator<String> stit = listOfItems.iterator();
		while (stit.hasNext()) {
			sb.append(stit.next());
			if (stit.hasNext()) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}

	private static boolean isCollectionEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isObjectEmpty(Object object) {
		if (object == null) {
			return true;
		} else if (object instanceof String) {
			if (((String) object).trim().length() == 0) {
				return true;
			}
		} else if (object instanceof Collection) {
			return isCollectionEmpty((Collection<?>) object);
		}
		return false;
	}
}
