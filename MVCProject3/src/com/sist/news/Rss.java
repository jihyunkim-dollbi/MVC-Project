package com.sist.news;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
// bind 파싱=> xml binding! 
/*
 * JAXP : PARSING => JAVA API FOR XML PARSE(설정 파일 읽기용- myBatis-SAX파싱, Spring-SAX파싱!) 
 * 					1) DOM (Document Object Model) => 메모리에 저장(수정, 삭제 , 추가 가능)
 * 					2) SAX (Simple Api for XML) => 읽기 전용!
 * JAXB : BIND =>  JAVA API FOR XML BINDING =>Annotation 이용하는 프로그램!(bid data용) 
 * 			   => 마샬 ==> Java class에 있는 데이터를 xml로 변환!
 * 			   => 언마샬 ==> xml 을 java object로 변환!
 * 
 * <rss> 								-> class => @XmlRootElement 여기서부터 채워라~ 
 * 	<Channel> 							-> class => @XmlElement
 * 		<item> 							-> class
 * 			<title></title> 			-> variable
 * 			<author></author>
 * 			<description></description>
 * 			<link></link>
 * 		</item>
 *  * 		<item>
 * 			<title></title>
 * 			<author></author>
 * 			<description></description>
 * 			<link></link>
 * 		</item>
 *  * 		<item>
 * 			<title></title>
 * 			<author></author>
 * 			<description></description>
 * 			<link></link>
 * 		</item>
 *  * 		<item>
 * 			<title></title>
 * 			<author></author>
 * 			<description></description>
 * 			<link></link>
 * 		</item>
 * 	</Channel>
 * </rss>
 * 
 *  ==> newssearch.naver.com/search.naver?where=rss&query=코로나
 *  	
 *  	클래스인지 변수인지를 설정을 제대로 하면 데이터를 읽어올 수 있다.
 *  	네이버/카카오.. 
 *  	
 *  
 *  
 *  
 * 
 * 
 * 
 */
@XmlRootElement
public class Rss {

	private Channel channel=new Channel();

	public Channel getChannel() {
		return channel;
	}
	
	@XmlElement // Bind
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
	
}
