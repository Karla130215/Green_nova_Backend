-- Inserts de prueba para la base de datos Green-Nova
-- Generado con 5 registros por tabla
-- Orden recomendado por llaves foráneas:
-- 1) usuarios
-- 2) productos
-- 3) contacto
-- 4) Pedidos
-- 5) detalle_pedido
-- 6) detalle_pedido_has_Pedidos

SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE `detalle_pedido_has_Pedidos`;
TRUNCATE TABLE `detalle_pedido`;
TRUNCATE TABLE `Pedidos`;
TRUNCATE TABLE `contacto`;
TRUNCATE TABLE `productos`;
TRUNCATE TABLE `usuarios`;

SET FOREIGN_KEY_CHECKS = 1;

-- =========================
-- Tabla: usuarios
-- =========================
INSERT INTO `usuarios`
(`id_usuario`, `nombre`, `telefono`, `email`, `password`)
VALUES
(1, 'Luis Hernández', '5512345678', 'luis@example.com', 'Luis1234'),
(2, 'Ana Martínez', '5523456789', 'ana@example.com', 'Ana1234'),
(3, 'Carlos López', '5534567890', 'carlos@example.com', 'Carlos1234'),
(4, 'María García', '5545678901', 'maria@example.com', 'Maria1234'),
(5, 'Jorge Ramírez', '5556789012', 'jorge@example.com', 'Jorge1234');

-- =========================
-- Tabla: productos
-- =========================
INSERT INTO `productos`
(`id_productos`, `nombre`, `tipo_producto`, `precio`, `imagen`, `r_luz`, `f_riego`, `funcion`, `descripcion`)
VALUES
(1, 'Monstera Deliciosa', 'Planta interior', 350.00, 'monstera.jpg', 'Luz indirecta', 'Cada 7 días', 'Decorativa', 'Planta tropical de hojas grandes'),
(2, 'Cactus Bola', 'Cactus', 120.00, 'cactus_bola.jpg', 'Luz directa', 'Cada 15 días', 'Decorativa', 'Cactus pequeño de bajo mantenimiento'),
(3, 'Lavanda', 'Planta aromática', 180.00, 'lavanda.jpg', 'Luz directa', 'Cada 5 días', 'Aromática', 'Planta con aroma relajante'),
(4, 'Helecho Boston', 'Planta interior', 220.00, 'helecho_boston.jpg', 'Sombra parcial', 'Cada 3 días', 'Purificadora', 'Planta ideal para interiores húmedos'),
(5, 'Suculenta Echeveria', 'Suculenta', 95.00, 'echeveria.jpg', 'Luz indirecta', 'Cada 10 días', 'Decorativa', 'Suculenta pequeña y resistente');

-- =========================
-- Tabla: contacto
-- =========================
INSERT INTO `contacto`
(`id_mensaje`, `nombre`, `apellido`, `email`, `mensaje`, `fecha_envio`)
VALUES
(1, 'Pedro', 'Sánchez', 'pedro@example.com', 'Quiero saber si tienen envío a domicilio.', '2026-07-01 10:30:00'),
(2, 'Laura', 'Pérez', 'laura@example.com', 'Me interesa comprar plantas para oficina.', '2026-07-02 12:15:00'),
(3, 'Sofía', 'Torres', 'sofia@example.com', '¿Tienen macetas disponibles?', '2026-07-03 09:45:00'),
(4, 'Miguel', 'Castillo', 'miguel@example.com', 'Necesito recomendaciones para plantas de sombra.', '2026-07-04 16:20:00'),
(5, 'Fernanda', 'Ruiz', 'fernanda@example.com', '¿Cuáles son sus horarios de atención?', '2026-07-05 18:05:00');

-- =========================
-- Tabla: Pedidos
-- =========================
INSERT INTO `Pedidos`
(`id_pedido`, `fecha_pedido`, `total`, `estado`, `usuarios_id_usuario`)
VALUES
(1, '2026-07-01', 350.00, 'Pagado', 1),
(2, '2026-07-02', 240.00, 'Pendiente', 2),
(3, '2026-07-03', 540.00, 'Enviado', 3),
(4, '2026-07-04', 220.00, 'Entregado', 4),
(5, '2026-07-05', 285.00, 'Cancelado', 5);

-- =========================
-- Tabla: detalle_pedido
-- =========================
INSERT INTO `detalle_pedido`
(`id_detalle`, `cantidad`, `precio_unidad`, `productos_id_productos`)
VALUES
(1, 1, 350.00, 1),
(2, 2, 120.00, 2),
(3, 3, 180.00, 3),
(4, 1, 220.00, 4),
(5, 3, 95.00, 5);

-- =========================
-- Tabla: detalle_pedido_has_Pedidos
-- =========================
INSERT INTO `detalle_pedido_has_Pedidos`
(`detalle_pedido_id_detalle`, `Pedidos_id_pedido`)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);
