/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 USE [master]
GO
/****** Object:  Database [ventas]    Script Date: 21/04/2021 20:13:38 ******/
CREATE DATABASE [ventas]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ventas', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\ventas.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'ventas_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\ventas_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [ventas] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ventas].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ventas] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ventas] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ventas] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ventas] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ventas] SET ARITHABORT OFF 
GO
ALTER DATABASE [ventas] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ventas] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ventas] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ventas] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ventas] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ventas] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ventas] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ventas] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ventas] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ventas] SET  ENABLE_BROKER 
GO
ALTER DATABASE [ventas] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ventas] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ventas] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ventas] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ventas] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ventas] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ventas] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ventas] SET RECOVERY FULL 
GO
ALTER DATABASE [ventas] SET  MULTI_USER 
GO
ALTER DATABASE [ventas] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ventas] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ventas] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ventas] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [ventas] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'ventas', N'ON'
GO
ALTER DATABASE [ventas] SET QUERY_STORE = OFF
GO
USE [ventas]
GO
/****** Object:  Table [dbo].[boleta]    Script Date: 21/04/2021 20:13:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[boleta](
	[num_bol] [varchar](10) NOT NULL,
	[cod_cli] [varchar](6) NOT NULL,
	[pre_tot] [varchar](10) NOT NULL,
	[fecha] [varchar](15) NOT NULL,
	[cod_ven] [varchar](6) NULL,
	[hora_boleta] [varchar](10) NULL,
	[tipoVenta] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[num_bol] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CajaRegistradora]    Script Date: 21/04/2021 20:13:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CajaRegistradora](
	[num_caj] [varchar](10) NOT NULL,
	[cod_ven] [varchar](6) NOT NULL,
	[pre_tot] [varchar](10) NOT NULL,
	[descuento] [varchar](10) NULL,
	[efect_tot] [varchar](10) NOT NULL,
	[fecha] [varchar](15) NOT NULL,
	[hora_boleta] [varchar](10) NOT NULL,
	[cod_cli] [varchar](6) NULL,
PRIMARY KEY CLUSTERED 
(
	[num_caj] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cliente]    Script Date: 21/04/2021 20:13:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cliente](
	[cod_cli] [varchar](6) NOT NULL,
	[nom_cli] [varchar](30) NOT NULL,
	[ape_cli] [varchar](30) NOT NULL,
	[sexo_cli] [varchar](1) NOT NULL,
	[dni_cli] [varchar](8) NOT NULL,
	[tel_cli] [varchar](9) NOT NULL,
	[ruc_cli] [varchar](11) NOT NULL,
	[email_cli] [varchar](30) NOT NULL,
	[dir_cli] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_cli] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[color]    Script Date: 21/04/2021 20:13:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[color](
	[cod_color] [varchar](6) NOT NULL,
	[des_color] [varchar](15) NOT NULL,
	[coment] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_color] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[compraMaterial]    Script Date: 21/04/2021 20:13:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[compraMaterial](
	[cod_com] [varchar](6) NOT NULL,
	[nom_mat] [varchar](30) NOT NULL,
	[tipo_mat] [varchar](15) NOT NULL,
	[fecha] [varchar](15) NOT NULL,
	[cantidad] [varchar](15) NOT NULL,
	[medida_cantidad] [varchar](15) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_com] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DescripOrden]    Script Date: 21/04/2021 20:13:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DescripOrden](
	[nro_orden] [varchar](6) NOT NULL,
	[cod_mat] [varchar](6) NOT NULL,
	[Tipo_mat] [varchar](20) NOT NULL,
	[cod_ven] [varchar](6) NOT NULL,
	[cod_maq] [varchar](6) NOT NULL,
	[tiempo_maq] [varchar](8) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[nro_orden] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[detalleboleta]    Script Date: 21/04/2021 20:13:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[detalleboleta](
	[num_bol] [varchar](10) NOT NULL,
	[cod_pro] [varchar](6) NOT NULL,
	[des_pro] [varchar](30) NOT NULL,
	[cant_pro] [varchar](3) NOT NULL,
	[pre_unit] [varchar](10) NOT NULL,
	[pre_venta] [varchar](10) NOT NULL,
	[talla_prod] [varchar](6) NOT NULL,
	[cod_color] [varchar](20) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleCajaRegistradora]    Script Date: 21/04/2021 20:13:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleCajaRegistradora](
	[num_caj] [varchar](10) NOT NULL,
	[cod_pro] [varchar](6) NOT NULL,
	[cod_talla] [varchar](6) NOT NULL,
	[precio] [varchar](10) NOT NULL,
	[cantidad] [varchar](6) NOT NULL,
	[pre_venta] [varchar](10) NOT NULL,
	[tipoVenta] [varchar](10) NOT NULL,
	[cod_color] [varchar](20) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[detallefactura]    Script Date: 21/04/2021 20:13:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[detallefactura](
	[num_fac] [varchar](10) NOT NULL,
	[cod_pro] [varchar](6) NOT NULL,
	[des_pro] [varchar](30) NOT NULL,
	[cant_pro] [varchar](3) NOT NULL,
	[pre_unit] [varchar](10) NOT NULL,
	[pre_tot] [varchar](10) NOT NULL,
	[talla_prod] [varchar](6) NOT NULL,
	[cod_color] [varchar](20) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EsperaCajaRegistradora]    Script Date: 21/04/2021 20:13:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EsperaCajaRegistradora](
	[num_caj] [varchar](10) NOT NULL,
	[cod_ven] [varchar](6) NOT NULL,
	[pre_tot] [varchar](10) NOT NULL,
	[descuento] [varchar](10) NULL,
	[efect_tot] [varchar](10) NULL,
	[fecha] [varchar](15) NOT NULL,
	[hora_boleta] [varchar](10) NOT NULL,
	[cod_cli] [varchar](6) NULL,
PRIMARY KEY CLUSTERED 
(
	[num_caj] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[EsperaDetalleCajaRegistradora]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[EsperaDetalleCajaRegistradora](
	[num_caj] [varchar](10) NOT NULL,
	[cod_pro] [varchar](6) NOT NULL,
	[cod_talla] [varchar](6) NOT NULL,
	[precio] [varchar](10) NOT NULL,
	[cantidad] [varchar](6) NOT NULL,
	[pre_venta] [varchar](10) NOT NULL,
	[tipoVenta] [varchar](10) NOT NULL,
	[cod_color] [varchar](20) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[factura]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[factura](
	[num_fac] [varchar](10) NOT NULL,
	[cod_cli] [varchar](6) NOT NULL,
	[ruc_cli] [varchar](11) NOT NULL,
	[subtotal] [varchar](10) NOT NULL,
	[igv] [varchar](40) NOT NULL,
	[total] [varchar](20) NOT NULL,
	[fec_fac] [varchar](20) NOT NULL,
	[cod_ven] [varchar](6) NULL,
	[hora_factura] [varchar](10) NULL,
	[tipoVenta] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[num_fac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FlujoCaja]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FlujoCaja](
	[cod_caj] [int] IDENTITY(1,1) NOT NULL,
	[num_caj] [varchar](10) NULL,
	[Ingreso] [varchar](10) NOT NULL,
	[Comentario] [varchar](100) NULL,
	[Fecha] [varchar](20) NOT NULL,
	[Hora] [varchar](6) NOT NULL,
	[TipoOperacion] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_caj] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FlujoProducto]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FlujoProducto](
	[cod_pro] [varchar](6) NOT NULL,
	[cod_talla] [varchar](6) NOT NULL,
	[cod_color] [varchar](6) NOT NULL,
	[Stock] [varchar](10) NOT NULL,
	[Fecha] [varchar](20) NOT NULL,
	[Hora] [varchar](6) NOT NULL,
	[TipoOperacion] [varchar](20) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[maquina]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[maquina](
	[cod_maq] [varchar](6) NOT NULL,
	[nom_maq] [varchar](30) NOT NULL,
	[precio_maq] [varchar](8) NOT NULL,
	[Tipo_pago] [varchar](20) NOT NULL,
	[vida_util] [varchar](15) NOT NULL,
	[medida_vida_util] [varchar](15) NOT NULL,
	[fecha] [varchar](15) NOT NULL,
	[stock] [varchar](6) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_maq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[material]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[material](
	[cod_mat] [varchar](6) NOT NULL,
	[nom_mat] [varchar](30) NOT NULL,
	[precio_mat] [varchar](8) NOT NULL,
	[Tipo_mat] [varchar](20) NOT NULL,
	[stock_mat] [varchar](15) NOT NULL,
	[medida_stock_mat] [varchar](15) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_mat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orden]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orden](
	[nro_ord] [varchar](6) NOT NULL,
	[fecha_pedido] [varchar](30) NOT NULL,
	[fecha_limite] [varchar](15) NOT NULL,
	[fecha_actual] [varchar](15) NOT NULL,
	[estado_orden] [varchar](15) NOT NULL,
	[cod_pro] [varchar](6) NOT NULL,
	[cantidad] [varchar](8) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[nro_ord] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[producto]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[producto](
	[cod_pro] [varchar](6) NOT NULL,
	[descripcion] [varchar](30) NOT NULL,
	[Stock] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_pro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sesion]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sesion](
	[estado] [varchar](15) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[talla]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[talla](
	[cod_talla] [varchar](6) NOT NULL,
	[des_talla] [varchar](4) NOT NULL,
	[coment] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_talla] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[talla_producto]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[talla_producto](
	[cod_talla] [varchar](6) NOT NULL,
	[cod_pro] [varchar](6) NOT NULL,
	[Stock] [varchar](10) NULL,
	[Precio_talla] [varchar](10) NULL,
	[Precio_talla_mayor] [varchar](10) NULL,
	[cod_color] [varchar](6) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[trabajo]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[trabajo](
	[cod_trab] [varchar](6) NOT NULL,
	[cod_ven] [varchar](6) NOT NULL,
	[nro_ord] [varchar](6) NOT NULL,
	[fecha] [varchar](15) NOT NULL,
	[horas] [varchar](15) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_trab] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[vendedor]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[vendedor](
	[cod_ven] [varchar](6) NOT NULL,
	[nom_ven] [varchar](30) NOT NULL,
	[ape_ven] [varchar](30) NOT NULL,
	[sexo_ven] [varchar](1) NOT NULL,
	[dni_ven] [varchar](8) NOT NULL,
	[tel_ven] [varchar](9) NOT NULL,
	[ruc_ven] [varchar](11) NOT NULL,
	[email_ven] [varchar](30) NOT NULL,
	[dir_ven] [varchar](50) NOT NULL,
	[cod_pass] [varchar](20) NOT NULL,
	[rol_negocio] [varchar](15) NULL,
PRIMARY KEY CLUSTERED 
(
	[cod_ven] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_cli]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_cli] ON [dbo].[boleta]
(
	[cod_cli] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_ven]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_ven] ON [dbo].[boleta]
(
	[cod_ven] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [num_caj]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [num_caj] ON [dbo].[CajaRegistradora]
(
	[num_caj] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_color]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_color] ON [dbo].[color]
(
	[cod_color] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_pro]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_pro] ON [dbo].[detalleboleta]
(
	[cod_pro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [num_bol]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [num_bol] ON [dbo].[detalleboleta]
(
	[num_bol] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_pro]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_pro] ON [dbo].[DetalleCajaRegistradora]
(
	[cod_pro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [num_caj]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [num_caj] ON [dbo].[DetalleCajaRegistradora]
(
	[num_caj] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_pro]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_pro] ON [dbo].[detallefactura]
(
	[cod_pro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [num_fac]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [num_fac] ON [dbo].[detallefactura]
(
	[num_fac] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [num_caj]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [num_caj] ON [dbo].[EsperaCajaRegistradora]
(
	[num_caj] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_pro]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_pro] ON [dbo].[EsperaDetalleCajaRegistradora]
(
	[cod_pro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [num_caj]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [num_caj] ON [dbo].[EsperaDetalleCajaRegistradora]
(
	[num_caj] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_cli]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_cli] ON [dbo].[factura]
(
	[cod_cli] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_ven]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_ven] ON [dbo].[factura]
(
	[cod_ven] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_pro]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_pro] ON [dbo].[producto]
(
	[cod_pro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_talla]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_talla] ON [dbo].[talla]
(
	[cod_talla] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_pro]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_pro] ON [dbo].[talla_producto]
(
	[cod_pro] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [cod_talla]    Script Date: 21/04/2021 20:13:40 ******/
CREATE NONCLUSTERED INDEX [cod_talla] ON [dbo].[talla_producto]
(
	[cod_talla] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CajaRegistradora] ADD  DEFAULT ('0') FOR [descuento]
GO
ALTER TABLE [dbo].[EsperaCajaRegistradora] ADD  DEFAULT ('0') FOR [descuento]
GO
ALTER TABLE [dbo].[boleta]  WITH CHECK ADD  CONSTRAINT [boleta_ibfk_1] FOREIGN KEY([cod_cli])
REFERENCES [dbo].[cliente] ([cod_cli])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[boleta] CHECK CONSTRAINT [boleta_ibfk_1]
GO
ALTER TABLE [dbo].[boleta]  WITH CHECK ADD  CONSTRAINT [boleta_ibfk_2] FOREIGN KEY([cod_ven])
REFERENCES [dbo].[vendedor] ([cod_ven])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[boleta] CHECK CONSTRAINT [boleta_ibfk_2]
GO
ALTER TABLE [dbo].[DescripOrden]  WITH CHECK ADD  CONSTRAINT [DescripOrden_ibfk_1] FOREIGN KEY([cod_mat])
REFERENCES [dbo].[material] ([cod_mat])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DescripOrden] CHECK CONSTRAINT [DescripOrden_ibfk_1]
GO
ALTER TABLE [dbo].[DescripOrden]  WITH CHECK ADD  CONSTRAINT [DescripOrden_ibfk_2] FOREIGN KEY([cod_ven])
REFERENCES [dbo].[vendedor] ([cod_ven])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DescripOrden] CHECK CONSTRAINT [DescripOrden_ibfk_2]
GO
ALTER TABLE [dbo].[DescripOrden]  WITH CHECK ADD  CONSTRAINT [DescripOrden_ibfk_3] FOREIGN KEY([cod_maq])
REFERENCES [dbo].[maquina] ([cod_maq])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DescripOrden] CHECK CONSTRAINT [DescripOrden_ibfk_3]
GO
ALTER TABLE [dbo].[detalleboleta]  WITH CHECK ADD  CONSTRAINT [detalleboleta_ibfk_1] FOREIGN KEY([num_bol])
REFERENCES [dbo].[boleta] ([num_bol])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[detalleboleta] CHECK CONSTRAINT [detalleboleta_ibfk_1]
GO
ALTER TABLE [dbo].[detalleboleta]  WITH CHECK ADD  CONSTRAINT [detalleboleta_ibfk_2] FOREIGN KEY([cod_pro])
REFERENCES [dbo].[producto] ([cod_pro])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[detalleboleta] CHECK CONSTRAINT [detalleboleta_ibfk_2]
GO
ALTER TABLE [dbo].[DetalleCajaRegistradora]  WITH CHECK ADD  CONSTRAINT [DetalleCajaRegistradora_ibfk_1] FOREIGN KEY([num_caj])
REFERENCES [dbo].[CajaRegistradora] ([num_caj])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DetalleCajaRegistradora] CHECK CONSTRAINT [DetalleCajaRegistradora_ibfk_1]
GO
ALTER TABLE [dbo].[DetalleCajaRegistradora]  WITH CHECK ADD  CONSTRAINT [DetalleCajaRegistradora_ibfk_2] FOREIGN KEY([cod_pro])
REFERENCES [dbo].[producto] ([cod_pro])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DetalleCajaRegistradora] CHECK CONSTRAINT [DetalleCajaRegistradora_ibfk_2]
GO
ALTER TABLE [dbo].[detallefactura]  WITH CHECK ADD  CONSTRAINT [detallefactura_ibfk_1] FOREIGN KEY([num_fac])
REFERENCES [dbo].[factura] ([num_fac])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[detallefactura] CHECK CONSTRAINT [detallefactura_ibfk_1]
GO
ALTER TABLE [dbo].[detallefactura]  WITH CHECK ADD  CONSTRAINT [detallefactura_ibfk_2] FOREIGN KEY([cod_pro])
REFERENCES [dbo].[producto] ([cod_pro])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[detallefactura] CHECK CONSTRAINT [detallefactura_ibfk_2]
GO
ALTER TABLE [dbo].[EsperaDetalleCajaRegistradora]  WITH CHECK ADD  CONSTRAINT [EsperaDetalleCajaRegistradoraa_ibfk_1] FOREIGN KEY([num_caj])
REFERENCES [dbo].[EsperaCajaRegistradora] ([num_caj])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[EsperaDetalleCajaRegistradora] CHECK CONSTRAINT [EsperaDetalleCajaRegistradoraa_ibfk_1]
GO
ALTER TABLE [dbo].[factura]  WITH CHECK ADD  CONSTRAINT [factura_ibfk_1] FOREIGN KEY([cod_cli])
REFERENCES [dbo].[cliente] ([cod_cli])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[factura] CHECK CONSTRAINT [factura_ibfk_1]
GO
ALTER TABLE [dbo].[factura]  WITH CHECK ADD  CONSTRAINT [factura_ibfk_2] FOREIGN KEY([cod_ven])
REFERENCES [dbo].[vendedor] ([cod_ven])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[factura] CHECK CONSTRAINT [factura_ibfk_2]
GO
ALTER TABLE [dbo].[FlujoCaja]  WITH CHECK ADD  CONSTRAINT [FlujoCaja_ibfk_1] FOREIGN KEY([num_caj])
REFERENCES [dbo].[CajaRegistradora] ([num_caj])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[FlujoCaja] CHECK CONSTRAINT [FlujoCaja_ibfk_1]
GO
ALTER TABLE [dbo].[talla_producto]  WITH CHECK ADD  CONSTRAINT [tallaProducto_ibfk_1] FOREIGN KEY([cod_talla])
REFERENCES [dbo].[talla] ([cod_talla])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[talla_producto] CHECK CONSTRAINT [tallaProducto_ibfk_1]
GO
ALTER TABLE [dbo].[talla_producto]  WITH CHECK ADD  CONSTRAINT [tallaProducto_ibfk_2] FOREIGN KEY([cod_pro])
REFERENCES [dbo].[producto] ([cod_pro])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[talla_producto] CHECK CONSTRAINT [tallaProducto_ibfk_2]
GO
ALTER TABLE [dbo].[talla_producto]  WITH CHECK ADD  CONSTRAINT [tallaProducto_ibfk_3] FOREIGN KEY([cod_color])
REFERENCES [dbo].[color] ([cod_color])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[talla_producto] CHECK CONSTRAINT [tallaProducto_ibfk_3]
GO
ALTER TABLE [dbo].[trabajo]  WITH CHECK ADD  CONSTRAINT [trabajo_ibfk_1] FOREIGN KEY([cod_ven])
REFERENCES [dbo].[vendedor] ([cod_ven])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[trabajo] CHECK CONSTRAINT [trabajo_ibfk_1]
GO
ALTER TABLE [dbo].[trabajo]  WITH CHECK ADD  CONSTRAINT [trabajo_ibfk_2] FOREIGN KEY([nro_ord])
REFERENCES [dbo].[Orden] ([nro_ord])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[trabajo] CHECK CONSTRAINT [trabajo_ibfk_2]
GO
/****** Object:  StoredProcedure [dbo].[buscar]    Script Date: 21/04/2021 20:13:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[buscar]		
  @coman as varchar(20)
  as
  SELECT * FROM vendedor WHERE CONCAT(cod_ven,nom_ven,ape_ven,dni_ven,ruc_ven,rol_negocio) LIKE '%'+@coman+'%'
GO
USE [master]
GO
ALTER DATABASE [ventas] SET  READ_WRITE 
GO


/**
 * Author:  Luis Ramos
 * Created: 21/04/2021
 */

