<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : dish-detail.xsl
    Created on : November 26, 2019, 1:39 PM
    Author     : Dell
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>

    <xsl:template name="CrawlDishDetail">
        <xsl:param name="doc" select="'Default doc value'"/>
        
        <xsl:variable name="detail" select="document($doc)"/>
        <xsl:variable name="dish-info" select="$detail//div[contains(@class,'detail_descipt')]"/>
        <dish>
            <name>
                <xsl:value-of select="$dish-info/h1[@itemprop='name']/text()"/>
            </name>
            <description>
                <xsl:value-of select="$dish-info/p[@itemprop='description']/text()"/>
            </description>
            <level>
                <xsl:value-of select="$dish-info/ul/li[1]/text()"/>
            </level>
            <recipeYield>
                <xsl:value-of select="$dish-info/ul/li[2]/span/text()"/>
            </recipeYield>
            <cookedTime>
                <xsl:value-of select="$dish-info/ul/li[3]/time/text()"/>
            </cookedTime>
            <img>
                <xsl:value-of select="$detail//div[contains(@class,'detail_img')]//img/@src"/>
            </img>
            <link>
                <xsl:value-of select="$doc"/>
            </link>
            <xsl:variable name="ingredients" 
                          select="$detail//div[contains(@class,'detail_ingredients')]"/>
            <xsl:for-each select="$ingredients/ul/li">
                <xsl:variable name="ingredient" select="span[@itemprop='ingredients']/text()"/>
                <xsl:if test="$ingredient">
                    <ingredient>
                        <xsl:choose>
                            <xsl:when test="string-length(substring-before($ingredient, ':')) > 0">
                                <name>
                                    <xsl:value-of select="substring-before($ingredient, ':')"/>
                                </name>
                            </xsl:when>
                            <xsl:when test="string-length(substring-before($ingredient, '  ')) > 0">
                                <name>
                                    <xsl:value-of select="substring-before($ingredient, '  ')"/>
                                </name>
                            </xsl:when>
                            <xsl:otherwise>
                                <name>
                                    <xsl:value-of select="$ingredient"/>
                                </name>
                            </xsl:otherwise>
                        </xsl:choose>
                        
                        <xsl:choose>
                            <xsl:when test="string-length(substring-after($ingredient, ':')) > 0">
                                <amount>
                                    <xsl:value-of select="substring-after($ingredient, ':')"/>
                                </amount>
                            </xsl:when>
                            <xsl:when test="string-length(substring-after($ingredient, ':')) = 0">
                                <amount>
                                    <xsl:value-of select="substring-after($ingredient, '  ')"/>
                                </amount>
                            </xsl:when>
                        </xsl:choose>
                    </ingredient>
                </xsl:if>
            </xsl:for-each>
            <!--</ingredients>-->
        </dish>
    </xsl:template>

</xsl:stylesheet>
