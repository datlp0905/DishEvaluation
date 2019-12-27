<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : nutrition-main.xsl
    Created on : November 27, 2019, 8:30 PM
    Author     : Dell
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:n="http://vansu.vn/"
                xmlns="http://vansu.vn/"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>
    <xsl:import href="nutrition-detail.xsl"/> 
    
    <xsl:template match="n:nutritions">
        <nutritions>
            <xsl:variable name="doc" select="document(@link)"/>
            <xsl:variable name="host" select="@host"/>
            <xsl:for-each select="$doc//tbody/tr">
                <xsl:variable name="group" select="td[1]/text()"/>
                <xsl:variable name="detailLink" select="concat($host,td[2]/a/@href)"/>
                <xsl:call-template name="CrawlDetail">
                    <xsl:with-param name="link" select="$detailLink"/>
                    <xsl:with-param name="nutriGroup" select="$group"/>
                </xsl:call-template>
            </xsl:for-each>
        </nutritions>
    </xsl:template>

</xsl:stylesheet>
