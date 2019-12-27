<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : nutrition-detail.xsl
    Created on : November 27, 2019, 8:42 PM
    Author     : Dell
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>

    <xsl:template name="CrawlDetail">
        <xsl:param name="link" select="'Default link value'"/>
        <xsl:param name="nutriGroup" select="'Default nutriGroup value'"/>
        <xsl:variable name="doc" select="document($link)"/>
        
        <nutrition>
            <nutriGroup>
                <xsl:value-of select="$nutriGroup"/>
            </nutriGroup>
            <calculatedPer></calculatedPer>
            <nutriName>
                <xsl:value-of select="$doc//div[@class='active section']"/>
            </nutriName>
            
            <xsl:variable name="nutri" select="$doc//table[@id='bangthanhphandinhduong']"/>
            
            <energyAmount>
                <xsl:value-of select="substring-before($nutri/tbody/tr[1]/td[2]/text(),' ')"/>
            </energyAmount>
            <energyUnit>
                <xsl:value-of select="substring-after($nutri/tbody/tr[1]/td[2]/text(),' ')"/>
            </energyUnit>
            
            <nutritionValues>
                <xsl:for-each select="$nutri/tbody/tr">
                    <nutritionValue>
                        <valueName>
                            <xsl:value-of select="td[1]/text()"/>
                        </valueName>
                        <amount>
                            <xsl:value-of select="substring-before(td[2]/text(),' ')"/>
                        </amount>
                        <unit>
                            <xsl:value-of select="substring-after(td[2]/text(),' ')"/>
                        </unit>
                    </nutritionValue>
                </xsl:for-each>
            </nutritionValues>
        </nutrition>
    </xsl:template>

</xsl:stylesheet>
