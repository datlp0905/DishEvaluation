<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : dish-category.xsl
    Created on : November 26, 2019, 1:12 PM
    Author     : Dell
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>
    <xsl:import href="dish-detail.xsl"/>
    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template name="CrawlCategory">
        <xsl:param name="doc" select="'Default doc value'"/>
        <xsl:param name="host" select="'Default host value'"/>
        <xsl:apply-templates select="$doc//div[@class='food_group food_resources']"/>
    </xsl:template>
    
    <xsl:template match="div[@class='food_group food_resources']">
        <xsl:for-each select="div[contains(@class,'hl02a')]">
            <mainCategory>
                <name>
                    <xsl:value-of select="h3/text()"/>
                </name>
                <xsl:for-each select=".//li/a">
                    <subCategory>
                        <name>
                            <xsl:value-of select="span/text()"/>
                        </name>
                        <xsl:variable name="categoryLink" select="@href"/>
                        <xsl:call-template name="CrawlDish">
                            <xsl:with-param name="link" select="$categoryLink"/>
                        </xsl:call-template>
                    </subCategory>
                </xsl:for-each>
            </mainCategory>
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template name="CrawlDish">
        <xsl:param name="link" select="'Default link value'"/>
        <xsl:variable name="dish-list" select="document($link)"/>
        
        <xsl:for-each select="$dish-list//div[contains(@class,'thanhvien_gr03_child')]">
            <xsl:variable name="dish-link" 
                      select="a/@href"/>
            <xsl:call-template name="CrawlDishDetail">
                <xsl:with-param name="doc" select="$dish-link"/>
            </xsl:call-template>
        </xsl:for-each>
        
        <xsl:apply-templates select="$dish-list//div[@class='thanhvien_page']/a[contains(@class,'next')]">
            <xsl:with-param name="nextLink" select="$link"/>
        </xsl:apply-templates>
    </xsl:template>
    
<!--    <xsl:template match="div[@class='thanhvien_page']/a[contains(@class,'next')][contains(@href,'http')]">
        <xsl:param name="mainCategory" select="'Default main category value'"/>
        <xsl:param name="subCategory" select="'Default sub category value'"/>
        <xsl:variable name="full-link" select="@href"/>
        <xsl:call-template name="CrawlDish">
            <xsl:with-param name="link" select="$full-link"/>
            <xsl:with-param name="mainCategory" select="$mainCategory"/>
            <xsl:with-param name="subCategory" select="$subCategory"/>
        </xsl:call-template>
    </xsl:template>
    <xsl:template match="div[@class='thanhvien_page']/a[contains(@class,'next')][not(contains(@href,'http'))]">
        <xsl:param name="mainCategory" select="'Default main category value'"/>
        <xsl:param name="subCategory" select="'Default sub category value'"/>
        <xsl:param name="nextLink" select="'Default next link value'"/>
        <xsl:variable name="short-link" select="@href"/>
        <xsl:variable name="full-link" select="concat($nextLink,$short-link)"/>
        <xsl:call-template name="CrawlDish">
            <xsl:with-param name="link" select="$full-link"/>
            <xsl:with-param name="mainCategory" select="$mainCategory"/>
            <xsl:with-param name="subCategory" select="$subCategory"/>
        </xsl:call-template>
    </xsl:template>-->
</xsl:stylesheet>
