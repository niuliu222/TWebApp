package com.study.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.study.converters.DateTimeConverter;
import com.study.utils.CustomRequestMappingHandlerMapping;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
	@Autowired
	private DateTimeConverter dateTimeConverter;

	public void addFormatters(FormatterRegistry registry)
	{
		registry.addConverter(dateTimeConverter);
	}

	// equivalents for <mvc:resources/> tags
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry)
	{

		CacheControl cc = CacheControl.maxAge(1, TimeUnit.HOURS);
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/commons/")
				.setCachePeriod(31556926).addResourceLocations("/resources/themeDefault/").setCachePeriod(31556926)
				.setCacheControl(cc);
		registry.setOrder(0);
	}

	@Bean
	public StringHttpMessageConverter getStringHttpMessageConverter()
	{
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(new MediaType("text", "plain", Charset.forName("UTF-8")));
		list.add(new MediaType("*", "*", Charset.forName("UTF-8")));
		stringConverter.setSupportedMediaTypes(list);

		return stringConverter;
	}

	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping()
	{
		RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
		handlerMapping.setOrder(0);
		// handlerMapping.setInterceptors(getInterceptors());
		return handlerMapping;
	}

	@Bean
	public FastJsonHttpMessageConverter getFastJson()
	{
		FastJsonHttpMessageConverter jsonConverter = new FastJsonHttpMessageConverter();
		List<MediaType> jsonList = new ArrayList<MediaType>();
		jsonList.add(MediaType.valueOf("application/json;charset=UTF-8"));
		jsonList.add(MediaType.valueOf("text/plain;charset=utf-8"));
		jsonList.add(MediaType.valueOf("text/html;charset=utf-8"));
		jsonConverter.setSupportedMediaTypes(jsonList);
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
		ValueFilter valueFilter = new ValueFilter()
		{
			public Object process(Object classRef, String key, Object value)
			{
				if (null == value)
				{
					value = "";
				}
				return value;
			}
		};
		fastJsonConfig.setSerializeFilters(valueFilter);
		jsonConverter.setFastJsonConfig(fastJsonConfig);

		return jsonConverter;
	}

	public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(new MediaType("text", "plain", Charset.forName("UTF-8")));
		list.add(new MediaType("*", "*", Charset.forName("UTF-8")));
		stringConverter.setSupportedMediaTypes(list);

		FastJsonHttpMessageConverter4 jsonConverter = new FastJsonHttpMessageConverter4();
		List<MediaType> jsonList = new ArrayList<MediaType>();
		jsonList.add(MediaType.valueOf("application/json;charset=UTF-8"));
		jsonList.add(MediaType.valueOf("text/plain;charset=utf-8"));
		jsonList.add(MediaType.valueOf("text/html;charset=utf-8"));
		jsonConverter.setSupportedMediaTypes(jsonList);
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		jsonConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(stringConverter);
		converters.add(jsonConverter);
	}
}