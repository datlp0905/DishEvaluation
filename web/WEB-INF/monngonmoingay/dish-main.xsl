<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : dish-category.xsl
    Created on : November 26, 2019, 11:23 AM
    Author     : Dell
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
                xmlns:x="https://monngonmoingay.com/"
                xmlns="https://monngonmoingay.com/"
                version="1.0">
    <xsl:output method="xml" omit-xml-declaration="yes" indent="yes" encoding="UTF-8"/>
    <xsl:import href="dish-category.xsl"/>
    
    <xsl:template match="x:dishes">
        <dishInfo>
            <xsl:variable name="doc" select="document(@link)"/>
            <xsl:variable name="host" select="@host"/>
            <xsl:call-template name="CrawlCategory">
                <xsl:with-param name="doc" select="$doc"/>
                <xsl:with-param name="host" select="$host"/>
            </xsl:call-template>
        </dishInfo>
    </xsl:template>

</xsl:stylesheet>
