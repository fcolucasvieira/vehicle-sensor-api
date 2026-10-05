CREATE TABLE detections (
    id UUID PRIMARY KEY,
    device VARCHAR(30) NOT NULL,
    vehicle VARCHAR(30) NOT NULL,
    direction VARCHAR(30) NOT NULL,
    confidence NUMERIC(5, 4) NOT NULL,
    received_at TIMESTAMPTZ NOT NULL,

    CONSTRAINT chk_detections_confidence
        CHECK (confidence >= 0 AND confidence <= 1),

    CONSTRAINT chk_detections_vehicle
        CHECK (vehicle IN ('MOTO', 'CARRO', 'ONIBUS', 'CAMINHAO')),

    CONSTRAINT chk_detections_direction
        CHECK (direction IN ('ENTRANDO', 'SAINDO'))
);