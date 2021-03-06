USE [master]
GO
/****** Object:  Database [DishEvaluation]    Script Date: 12/11/2019 1:09:08 PM ******/
CREATE DATABASE [DishEvaluation]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DishEvaluation', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\DishEvaluation.mdf' , SIZE = 7168KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DishEvaluation_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\DishEvaluation_log.ldf' , SIZE = 1792KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [DishEvaluation] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DishEvaluation].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DishEvaluation] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DishEvaluation] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DishEvaluation] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DishEvaluation] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DishEvaluation] SET ARITHABORT OFF 
GO
ALTER DATABASE [DishEvaluation] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DishEvaluation] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DishEvaluation] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DishEvaluation] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DishEvaluation] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DishEvaluation] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DishEvaluation] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DishEvaluation] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DishEvaluation] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DishEvaluation] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DishEvaluation] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DishEvaluation] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DishEvaluation] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DishEvaluation] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DishEvaluation] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DishEvaluation] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DishEvaluation] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DishEvaluation] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [DishEvaluation] SET  MULTI_USER 
GO
ALTER DATABASE [DishEvaluation] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DishEvaluation] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DishEvaluation] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DishEvaluation] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [DishEvaluation] SET DELAYED_DURABILITY = DISABLED 
GO
USE [DishEvaluation]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](200) NULL,
	[Role] [varchar](50) NULL,
	[Lastname] [nvarchar](50) NULL,
	[Firstname] [nvarchar](50) NULL,
	[Weight] [float] NULL,
	[Height] [float] NULL,
	[DoB] [date] NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedAt] [datetime] NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BMIConstanst]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BMIConstanst](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[MaleWeightAmount] [float] NULL,
	[MaleHeightAmount] [float] NULL,
	[MaleAgeAmount] [float] NULL,
	[MaleEpsilon] [float] NULL,
	[FemaleWeightAmount] [float] NULL,
	[FemaleHeightAmount] [float] NULL,
	[FemaleAgeAmount] [float] NULL,
	[FemaleEpsilon] [float] NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedAt] [datetime] NULL,
 CONSTRAINT [PK_BMIConstanst] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BMILevelOfWork]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BMILevelOfWork](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Info] [nvarchar](100) NULL,
	[Value] [float] NULL,
	[BMI_ID] [int] NULL,
 CONSTRAINT [PK_BMILevelOfWork] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BMINeed]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BMINeed](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Info] [nvarchar](100) NULL,
	[Value] [float] NULL,
	[BMI_ID] [int] NULL,
 CONSTRAINT [PK_BMINeed] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Dish]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Dish](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[Description] [nvarchar](max) NULL,
	[Level] [nvarchar](50) NULL,
	[RecipeYield] [nvarchar](50) NULL,
	[CookTime] [nvarchar](50) NULL,
	[Img] [nvarchar](max) NULL,
	[Link] [nvarchar](max) NULL,
	[HashName] [int] NULL,
	[HashContent] [int] NULL,
	[CreatedBy] [varchar](50) NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedBy] [varchar](50) NULL,
	[UpdatedAt] [datetime] NULL,
 CONSTRAINT [PK_Dish] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[DishSubCategory]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DishSubCategory](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[SubCateId] [int] NULL,
	[DishId] [int] NULL,
 CONSTRAINT [PK_DishSubCategory] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Ingredient]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ingredient](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](max) NOT NULL,
	[Amount] [float] NULL,
	[Unit] [nvarchar](50) NULL,
	[StandardAmount] [float] NULL,
	[NutritionHash] [int] NULL,
	[NutritionRecommend] [int] NULL,
	[Valuable] [bit] NULL,
	[Energy] [float] NULL,
	[DishId] [int] NULL,
	[HashName] [int] NULL,
	[HashContent] [int] NULL,
 CONSTRAINT [PK_Ingredient] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[IngredientValueDictionary]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[IngredientValueDictionary](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IngredentName] [nvarchar](100) NULL,
	[HashValueName] [int] NULL,
	[CreatedAt] [datetime] NULL,
 CONSTRAINT [PK_IngredientValueDictionary] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MainCategory]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MainCategory](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[HashName] [int] NULL,
	[CreatedAt] [datetime] NOT NULL,
	[UpdatedAt] [datetime] NULL,
 CONSTRAINT [PK_MainCategory] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Nutrition]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Nutrition](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[GroupId] [int] NULL,
	[EnergyAmount] [float] NULL,
	[EnergyUnit] [varchar](10) NULL,
	[CalculatedPer] [float] NULL,
	[HashName] [int] NULL,
	[HashContent] [int] NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedAt] [datetime] NULL,
 CONSTRAINT [PK_Nutrition] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NutritionGroup]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NutritionGroup](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[HashName] [int] NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedAt] [datetime] NULL,
 CONSTRAINT [PK_NutritionGroup] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NutritionValue]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NutritionValue](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Amount] [float] NULL,
	[Unit] [varchar](10) NULL,
	[NutriId] [int] NULL,
	[HashName] [int] NULL,
	[HashContent] [int] NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedAt] [datetime] NULL,
 CONSTRAINT [PK_NutritionValue] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[StandardUnitDictionary]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StandardUnitDictionary](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IngredientName] [nvarchar](100) NULL,
	[Unit] [nvarchar](50) NOT NULL,
	[EqualTo] [float] NOT NULL,
	[CreatedAt] [datetime] NULL,
 CONSTRAINT [PK_StandardUnitDictionary] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SubCategory]    Script Date: 12/11/2019 1:09:08 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SubCategory](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[HashName] [int] NULL,
	[MainCateId] [int] NULL,
	[CreatedAt] [datetime] NULL,
	[UpdatedAt] [datetime] NULL,
 CONSTRAINT [PK_SubCategory] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Index [IX_DishSubCategory]    Script Date: 12/11/2019 1:09:08 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_DishSubCategory] ON [dbo].[DishSubCategory]
(
	[DishId] ASC,
	[SubCateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_IngredientValueDictionary]    Script Date: 12/11/2019 1:09:08 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [IX_IngredientValueDictionary] ON [dbo].[IngredientValueDictionary]
(
	[IngredentName] ASC,
	[HashValueName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BMILevelOfWork]  WITH CHECK ADD  CONSTRAINT [FK_BMILevelOfWork_BMIConstanst] FOREIGN KEY([BMI_ID])
REFERENCES [dbo].[BMIConstanst] ([Id])
GO
ALTER TABLE [dbo].[BMILevelOfWork] CHECK CONSTRAINT [FK_BMILevelOfWork_BMIConstanst]
GO
ALTER TABLE [dbo].[BMINeed]  WITH CHECK ADD  CONSTRAINT [FK_BMINeed_BMIConstanst] FOREIGN KEY([BMI_ID])
REFERENCES [dbo].[BMIConstanst] ([Id])
GO
ALTER TABLE [dbo].[BMINeed] CHECK CONSTRAINT [FK_BMINeed_BMIConstanst]
GO
ALTER TABLE [dbo].[DishSubCategory]  WITH CHECK ADD  CONSTRAINT [FK_DishSubCategory_Dish] FOREIGN KEY([DishId])
REFERENCES [dbo].[Dish] ([Id])
GO
ALTER TABLE [dbo].[DishSubCategory] CHECK CONSTRAINT [FK_DishSubCategory_Dish]
GO
ALTER TABLE [dbo].[DishSubCategory]  WITH CHECK ADD  CONSTRAINT [FK_DishSubCategory_SubCategory] FOREIGN KEY([SubCateId])
REFERENCES [dbo].[SubCategory] ([Id])
GO
ALTER TABLE [dbo].[DishSubCategory] CHECK CONSTRAINT [FK_DishSubCategory_SubCategory]
GO
ALTER TABLE [dbo].[Ingredient]  WITH CHECK ADD  CONSTRAINT [FK_Ingredient_Dish] FOREIGN KEY([DishId])
REFERENCES [dbo].[Dish] ([Id])
GO
ALTER TABLE [dbo].[Ingredient] CHECK CONSTRAINT [FK_Ingredient_Dish]
GO
ALTER TABLE [dbo].[Nutrition]  WITH CHECK ADD  CONSTRAINT [FK_Nutrition_NutritionGroup] FOREIGN KEY([GroupId])
REFERENCES [dbo].[NutritionGroup] ([Id])
GO
ALTER TABLE [dbo].[Nutrition] CHECK CONSTRAINT [FK_Nutrition_NutritionGroup]
GO
ALTER TABLE [dbo].[NutritionValue]  WITH CHECK ADD  CONSTRAINT [FK_NutritionValue_Nutrition] FOREIGN KEY([NutriId])
REFERENCES [dbo].[Nutrition] ([Id])
GO
ALTER TABLE [dbo].[NutritionValue] CHECK CONSTRAINT [FK_NutritionValue_Nutrition]
GO
ALTER TABLE [dbo].[SubCategory]  WITH CHECK ADD  CONSTRAINT [FK_SubCategory_MainCategory] FOREIGN KEY([MainCateId])
REFERENCES [dbo].[MainCategory] ([Id])
GO
ALTER TABLE [dbo].[SubCategory] CHECK CONSTRAINT [FK_SubCategory_MainCategory]
GO
USE [master]
GO
ALTER DATABASE [DishEvaluation] SET  READ_WRITE 
GO
